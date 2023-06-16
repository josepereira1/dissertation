package com.ecommerce.cp.core.business.saga.sagas.updateproduct.features;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.cp.core.business.logic.product.command.CommandProduct;
import com.ecommerce.cp.core.business.logic.product.query.QueryProduct;
import com.ecommerce.cp.core.business.logs.ILogs;
import com.ecommerce.cp.core.business.messaging.resources.IMessage;
import com.ecommerce.cp.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cp.core.business.resources.CounterMeasure;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.business.exceptions.product.SomeArgumentsRelatedToPriceInFaultException;
import com.ecommerce.cp.core.business.saga.sagas.updateproduct.mappers.in.IUpdateProductSagaInMapper;
import com.ecommerce.cp.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.cp.core.business.saga.sagas.utils.CommonSaga;
import com.ecommerce.cp.core.business.saga.sagas.updateproduct.mappers.in.dtos.ProductDTO;
import com.ecommerce.cp.core.business.saga.sagas.utils.resources.SagaMetadata;
import com.ecommerce.cp.core.business.saga.sagas.utils.resources.SagaStatus;
import com.ecommerce.cp.core.ports.out.messaging.saga.ISagaOrchestrator;
import com.ecommerce.cp.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.cp.core.ports.out.repository.product.IQueryProductRepository;
import com.ecommerce.cp.core.ports.out.repository.updateproductsaga.ICommandUpdateProductSagaRepository;
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
    private ILogs logs;

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandUpdateProductSagaRepository commandUpdateProductSagaRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private CommandProduct commandProduct;

    @Autowired
    private QueryProduct queryProduct;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    public void callLocalTransaction(IMessage message) {
        Product product = null;
        try {
            JsonElement data = message.getData();
            String sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
            try {
                do{
                    try{
                        product = lock(sagaId, data);
                    } catch (ProductWithThatIdNotExistException e){
                        e.printStackTrace();
                        //unlock(product.getId(), sagaId);
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
            } catch (ProductWithThatIdNotExistException e){
                e.printStackTrace();
                //unlock(product.getId(), sagaId.getSagaId());
                logs.logError("[SAGA] - " + e.getMessage() != null ? e.getMessage() : "Product not exists." + " - message: " + message);
                sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR, 404, e.getMessage() != null ? e.getMessage() : "Product not exists.", message.getMetadata(), message.getData());
                return;
            } catch (SomeArgumentsRelatedToPriceInFaultException e) {
                e.printStackTrace();
                unlock(product.getId(), sagaId);
                logs.logError("[SAGA] - " + e.getMessage() != null ? e.getMessage() : "Arguments in fault." + " - message: " + message);
                sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR, 500, e.getMessage() != null ? e.getMessage() : "Some arguments related to price in fault.", message.getMetadata(), message.getData());
                return;
            } catch (Exception e) {
                e.printStackTrace();
                unlock(product.getId(), sagaId);
                logs.logError("[SAGA] - " + e.getMessage() != null ? e.getMessage() : "Internal server error." + " - message: " + message);
                sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR, 500, e.getMessage() != null ? e.getMessage() : "Internal server error.", message.getMetadata(), message.getData());
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,500, e.getMessage() != null ? e.getMessage() : "Internal server error.", message.getMetadata(), message.getData());
            return;
        }
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS, 200, "", message.getMetadata(), message.getData());
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean localTransaction(IMessage message) throws EcommerceBusinessLogicException {
        UpdateProductSaga updateProductSaga = new UpdateProductSaga();
        String sagaId = null;
        ProductDTO input = null;
        try {
            JsonElement data = message.getData();
            sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
            input = gson.fromJson(data, ProductDTO.class);
            Product currentProduct = queryProduct.readProduct(input.getId());
            if (currentProduct.getCountermeasure() == null || (currentProduct.getCountermeasure() != null && currentProduct.getOwner() != null && currentProduct.getCountermeasure().equals(CounterMeasure.BLOCKED) && !currentProduct.getOwner().equals(sagaId)))
                return false;
            Product updatedProduct = IUpdateProductSagaInMapper.toProduct(input);
            updatedProduct.setOwner(sagaId);
            updatedProduct.setCountermeasure(CounterMeasure.BLOCKED);
            updateProductSaga.setSagaId(sagaId);
            updateProductSaga.setProductId(input.getId());
            updateProductSaga.setProductBackup(IUpdateProductSagaInMapper.toUpdateProductSagaProductBackup(currentProduct));
            updatedProduct = commandProduct.updateProduct(updatedProduct.getId(), updatedProduct);
            if (updatedProduct.getAmountInDiscount() != null)
                data.getAsJsonObject().addProperty("amountInDiscount", updatedProduct.getAmountInDiscount());
            if (updatedProduct.getFinalPrice() != null)
                data.getAsJsonObject().addProperty("finalPrice", updatedProduct.getFinalPrice());
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
        Optional<Product> optional = queryProductRepository.findById(id);
        if(!optional.isPresent()) throw new Exception();
        Product product = optional.get();
        if(product.getCountermeasure() != null && product.getOwner() != null && product.getCountermeasure().equals(CounterMeasure.BLOCKED) && product.getOwner().equals(sagaId)) {
            product.setCountermeasure(CounterMeasure.UNLOCKED);
            commandProductRepository.save(product);
        }
    }
}
