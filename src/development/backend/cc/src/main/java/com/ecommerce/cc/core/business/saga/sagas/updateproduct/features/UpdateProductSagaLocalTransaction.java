package com.ecommerce.cc.core.business.saga.sagas.updateproduct.features;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.exceptions.category.CategoryNotExistException;
import com.ecommerce.cc.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.cc.core.business.logic.product.command.CommandProduct;
import com.ecommerce.cc.core.business.logs.ILogs;
import com.ecommerce.cc.core.business.messaging.resources.IMessage;
import com.ecommerce.cc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cc.core.business.resources.CounterMeasure;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.business.saga.sagas.updateproduct.mappers.IUpdateProductSagaInMapper;
import com.ecommerce.cc.core.business.saga.sagas.updateproduct.mappers.dtos.ProductAndAddCategoryIdsAndRemoveCategoryIdsDTO;
import com.ecommerce.cc.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.cc.core.business.saga.sagas.updateproduct.resources.UpdateProductSagaProductBackup;
import com.ecommerce.cc.core.business.saga.utils.CommonSaga;
import com.ecommerce.cc.core.business.saga.utils.resources.SagaStatus;
import com.ecommerce.cc.core.ports.out.messaging.saga.ISagaOrchestrator;
import com.ecommerce.cc.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.cc.core.ports.out.repository.product.IQueryProductRepository;
import com.ecommerce.cc.core.ports.out.repository.saga.updateproduct.ICommandUpdateProductSagaRepository;
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
    private ISagaOrchestrator sagaOrchestrator;

    @Autowired
    private ILogs logs;

    public void callLocalTransaction(IMessage message){
        Product product = null;
        try{
            JsonElement data = message.getData();
            String sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
            try{
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
                } while (!localTransaction(message));
            } catch (ProductWithThatIdNotExistException e){
                e.printStackTrace();
                logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
                sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,409, e.getMessage() != null ? e.getMessage() : "Product not exists.", message.getMetadata(), message.getData());
                return;
            } catch (CategoryNotExistException e){
                e.printStackTrace();
                unlock(product.getId(), sagaId);
                logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
                sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,409, e.getMessage() != null ? e.getMessage() : "Category not exists.", message.getMetadata(), message.getData());
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

    //  caso o EAGER nas categorias do produto, não seja possível, utilizar o produto retornado no lockResource;

    @Transactional(rollbackFor = Exception.class)
    public boolean localTransaction(IMessage message) throws EcommerceBusinessLogicException {
        UpdateProductSaga updateProductSaga;
        ProductAndAddCategoryIdsAndRemoveCategoryIdsDTO input = null;
        String sagaId = null;
        try {
            JsonElement data = message.getData();
            sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
            input = gson.fromJson(data, ProductAndAddCategoryIdsAndRemoveCategoryIdsDTO.class);
            Optional<Product> optional = queryProductRepository.findById(input.getId());
            if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(input.getId()).build();
            Product currentProduct = optional.get();
            if (currentProduct == null || (currentProduct.getCountermeasure() != null && currentProduct.getOwner() != null && !currentProduct.getOwner().equals(sagaId)))
                return false;
            Product updatedProduct = IUpdateProductSagaInMapper.toProduct(input);
            UpdateProductSagaProductBackup productBackup = IUpdateProductSagaInMapper.toUpdateProductSagaProductBackup(currentProduct);
            updateProductSaga = new UpdateProductSaga();
            updateProductSaga.setSagaId(sagaId);
            updateProductSaga.setProductId(input.getId());
            updateProductSaga.setProductBackup(productBackup);
            commandProduct.updateProduct(updatedProduct.getId(), updatedProduct);
            updateProductSaga.setSagaStatus(SagaStatus.PENDING);
            commandUpdateProductSagaRepository.save(updateProductSaga);
            return true;
        }catch (Exception e){
            updateProductSaga = new UpdateProductSaga();
            if(sagaId != null) updateProductSaga.setSagaId(sagaId);
            if(input != null) updateProductSaga.setProductId(input.getId());
            updateProductSaga.setSagaStatus(SagaStatus.ERROR);
            commandUpdateProductSagaRepository.save(updateProductSaga);
            throw e;
        }
    }


    public Product lock(String sagaId, JsonElement data) throws EcommerceBusinessLogicException, InterruptedException {
        Product product;
        ProductAndAddCategoryIdsAndRemoveCategoryIdsDTO input = gson.fromJson(data, ProductAndAddCategoryIdsAndRemoveCategoryIdsDTO.class);
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
