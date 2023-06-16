package com.ecommerce.cc.core.business.saga.sagas.createproduct.features;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.exceptions.category.CategoryNotExistException;
import com.ecommerce.cc.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.cc.core.business.logic.product.command.CommandProduct;
import com.ecommerce.cc.core.business.logs.ILogs;
import com.ecommerce.cc.core.business.messaging.resources.IMessage;
import com.ecommerce.cc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cc.core.business.resources.CounterMeasure;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.business.saga.sagas.createproduct.mappers.ICreateProductSagaInMapper;
import com.ecommerce.cc.core.business.saga.sagas.createproduct.mappers.dtos.ProductAndCategoryIdsDTO;
import com.ecommerce.cc.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.cc.core.business.saga.utils.CommonSaga;
import com.ecommerce.cc.core.business.saga.utils.resources.SagaStatus;
import com.ecommerce.cc.core.ports.out.messaging.saga.ISagaOrchestrator;
import com.ecommerce.cc.core.ports.out.repository.saga.createproduct.ICommandCreateProductSagaRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateProductSagaLocalTransaction {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandCreateProductSagaRepository commandCreateProductSagaRepository;

    @Autowired
    private CommandProduct commandProduct;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    @Autowired
    private ILogs logs;

    public void callLocalTransaction(IMessage message){
        try{
            localTransaction(message);
        } catch (ProductWithThatIdNotExistException e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,409, e.getMessage() != null ? e.getMessage() : "Product not exists.", message.getMetadata(), message.getData());
            return;
        } catch (CategoryNotExistException e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,409, e.getMessage() != null ? e.getMessage() : "Category not exists.", message.getMetadata(), message.getData());
            return;
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,500, e.getMessage() != null ? e.getMessage() : "Internal server error.", message.getMetadata(), message.getData());
            return;
        }
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS,200, "", message.getMetadata(), message.getData());
    }

    @Transactional(rollbackFor = Exception.class)
    public void localTransaction(IMessage message) throws EcommerceBusinessLogicException {
        CreateProductSaga createProductSaga;
        String sagaId = null;
        Product product = null;
        try {
            JsonElement data = message.getData();
            sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
            ProductAndCategoryIdsDTO input = gson.fromJson(data, ProductAndCategoryIdsDTO.class);
            product = ICreateProductSagaInMapper.toProduct(input);
            product.setOwner(sagaId);
            product.setCountermeasure(CounterMeasure.BLOCKED); //  blocked
            createProductSaga = new CreateProductSaga();
            createProductSaga.setSagaId(sagaId);
            createProductSaga.setProductId(product.getId());
            commandProduct.createProduct(product, null);
            createProductSaga.setSagaStatus(SagaStatus.PENDING);
            commandCreateProductSagaRepository.save(createProductSaga);
        }catch (Exception e){
            createProductSaga = new CreateProductSaga();
            if(sagaId != null) createProductSaga.setSagaId(sagaId);
            if(product != null) createProductSaga.setProductId(product.getId());
            createProductSaga.setSagaStatus(SagaStatus.ERROR);
            commandCreateProductSagaRepository.save(createProductSaga);
            throw e;
        }
    }
}
