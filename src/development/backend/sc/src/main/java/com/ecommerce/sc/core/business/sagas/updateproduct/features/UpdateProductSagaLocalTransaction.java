package com.ecommerce.sc.core.business.sagas.updateproduct.features;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.exceptions.shoppingcart.ProductWithThatIdNotExistException;
import com.ecommerce.sc.core.business.logic.product.command.CommandProduct;
import com.ecommerce.sc.core.business.logs.ILogs;
import com.ecommerce.sc.core.business.messaging.resources.IMessage;
import com.ecommerce.sc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.sc.core.business.resources.CounterMeasure;
import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.business.sagas.updateproduct.mappers.IUpdateProductSagaInMapper;
import com.ecommerce.sc.core.business.sagas.updateproduct.mappers.dtos.ProductDTO;
import com.ecommerce.sc.core.business.sagas.updateproduct.resources.UpdateProductSagaProductBackup;
import com.ecommerce.sc.core.business.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.sc.core.business.sagas.utils.CommonSaga;
import com.ecommerce.sc.core.business.sagas.utils.resources.SagaStatus;
import com.ecommerce.sc.core.ports.in.product.IQueryProduct;
import com.ecommerce.sc.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.sc.core.ports.out.repository.product.IQueryProductRepository;
import com.ecommerce.sc.core.ports.out.repository.updateproduct.ICommandUpdateProductSagaRepository;
import com.ecommerce.sc.core.ports.out.saga.ISagaOrchestrator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateProductSagaLocalTransaction {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandUpdateProductSagaRepository commandUpdateProductSagaRepository;

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private CommandProduct commandProduct;

    @Autowired
    private IQueryProduct queryProduct;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    @Autowired
    private ILogs logs;

    @Transactional
    public void callLocalTransaction(IMessage message){
        try {
            JsonElement data = message.getData();
            String sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
            Product product = null;
            try {
                do{
                    try{
                        product = lock(sagaId, data);
                    } catch (ProductWithThatIdNotExistException e){
                        e.printStackTrace();
                        logs.logError("[SAGA] - " + e.getMessage() != null ? e.getMessage() : "Product not exists." + " - message: " + message);
                        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR, 404, e.getMessage() != null ? e.getMessage() : "Product not exists.", message.getMetadata(), message.getData());
                        return;
                    } catch (Exception e){
                        e.printStackTrace();
                        logs.logError("[SAGA] - " + e.getMessage() != null ? e.getMessage() : "Internal server error." + " - message: " + message);
                        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR, 500, e.getMessage() != null ? e.getMessage() : "Internal server error.", message.getMetadata(), message.getData());
                        return;
                    }
                }while (!localTransaction(message));
            } catch (ProductWithThatIdNotExistException e) {
                e.printStackTrace();
                unlock(product.getId(), sagaId);
                logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
                sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,404, e.getMessage() != null ? e.getMessage() : "Product not exists.", message.getMetadata(), message.getData());
                return;
            } catch (Exception e){
                e.printStackTrace();
                unlock(product.getId(), sagaId);
                logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
                sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,500, e.getMessage() != null ? e.getMessage() : "Internal server error.", message.getMetadata(), message.getData());
                return;
            }
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,500, e.getMessage() != null ? e.getMessage() : "Internal server error.", message.getMetadata(), message.getData());
            return;
        }
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS,200, "", message.getMetadata(), message.getData());
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean localTransaction(IMessage message) throws Exception {
        UpdateProductSaga updateProductSaga = new UpdateProductSaga(); String sagaId = null; ProductDTO input = null;
        try {
            JsonElement data = message.getData();
            sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
            input = gson.fromJson(data, ProductDTO.class);
            Product currentProduct = queryProduct.readProduct(input.getId());
            if (currentProduct.getCountermeasure() == null || (currentProduct.getCountermeasure() != null && currentProduct.getOwner() != null && currentProduct.getCountermeasure().equals(CounterMeasure.BLOCKED) && !currentProduct.getOwner().equals(sagaId)))
                return false;
            UpdateProductSagaProductBackup productBackup = IUpdateProductSagaInMapper.toUpdateProductSagaProductBackup(currentProduct);
            Product updatedProduct = IUpdateProductSagaInMapper.toProduct(input);
            updateProductSaga.setSagaId(sagaId);
            updateProductSaga.setProductId(input.getId());
            updateProductSaga.setProductBackup(productBackup);
            commandProduct.updateProduct(updatedProduct.getId(), updatedProduct);
            updateProductSaga.setSagaStatus(SagaStatus.PENDING);
            commandUpdateProductSagaRepository.save(updateProductSaga);
            return true;
        }catch (Exception e){
            if(sagaId != null) updateProductSaga.setSagaId(sagaId);
            if(input != null) updateProductSaga.setProductId(input.getId());
            updateProductSaga.setSagaStatus(SagaStatus.ERROR);
            commandUpdateProductSagaRepository.save(updateProductSaga);
            throw e;
        }
    }

    public Product lock(String sagaId, JsonElement data) throws EcommerceBusinessLogicException, InterruptedException {
        Product product;
        ProductDTO input = gson.fromJson(data, ProductDTO.class);
        while (true){
            if((product = lockProduct(input.getId(), sagaId)) != null) break;
            else {
                logs.logInfo("[SAGA] - Trying get lock. product_id:" + input.getId() + " - sagaId: " + sagaId);
                Thread.sleep(500);
                continue;
            }
        }
        return product;
    }

    @Transactional(rollbackFor = Exception.class)
    public Product lockProduct(String id, String sagaId) throws EcommerceBusinessLogicException {
        //Optional<Product> optional = queryProductRepository.findOneForUpdate(id);
        Optional<Product> optional = queryProductRepository.findById(id);
        if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(id).build();
        Product currentProduct = optional.get();
        if(currentProduct.getCountermeasure() != null && currentProduct.getOwner() != null && currentProduct.getCountermeasure().equals(CounterMeasure.UNLOCKED)) {
            currentProduct.setOwner(sagaId);
            currentProduct.setCountermeasure(CounterMeasure.BLOCKED);
            commandProductRepository.save(currentProduct);
            return currentProduct;
        }
        else {
            commandProductRepository.save(currentProduct);
            return null;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void unlock(String id, String sagaId) throws Exception {
        //Optional<Product> optional = queryProductRepository.findOneForUpdate(id);
        Optional<Product> optional = queryProductRepository.findById(id);
        if(!optional.isPresent()) throw new Exception();
        Product product = optional.get();
        if(product.getCountermeasure() != null && product.getOwner() != null && product.getCountermeasure().equals(CounterMeasure.BLOCKED) && product.getOwner().equals(sagaId)) {
            product.setCountermeasure(CounterMeasure.UNLOCKED);
            commandProductRepository.save(product);
        }
    }
}
