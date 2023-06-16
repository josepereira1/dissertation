package com.ecommerce.sc.core.business.sagas.createproduct.features;

import com.ecommerce.sc.core.business.exceptions.shoppingcart.ProductWithIdAlreadyExistsException;
import com.ecommerce.sc.core.business.logic.product.command.CommandProduct;
import com.ecommerce.sc.core.business.logs.ILogs;
import com.ecommerce.sc.core.business.messaging.resources.IMessage;
import com.ecommerce.sc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.sc.core.business.resources.CounterMeasure;
import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.business.sagas.createproduct.mappers.ICreateProductSagaInMapper;
import com.ecommerce.sc.core.business.sagas.createproduct.mappers.dtos.ProductDTO;
import com.ecommerce.sc.core.business.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.sc.core.business.sagas.utils.CommonSaga;
import com.ecommerce.sc.core.business.sagas.utils.resources.SagaStatus;
import com.ecommerce.sc.core.ports.out.repository.createproductsaga.ICommandCreateProductSagaRepository;
import com.ecommerce.sc.core.ports.out.saga.ISagaOrchestrator;
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
        } catch (ProductWithIdAlreadyExistsException e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,409, e.getMessage() != null ? e.getMessage() : "Product already exists.", message.getMetadata(), message.getData());
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
    public void localTransaction(IMessage message) throws Exception {
        CreateProductSaga createProductSaga = new CreateProductSaga(); String sagaId = null; Product product = null;
        try {
            JsonElement data = message.getData();
            sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
            ProductDTO input = gson.fromJson(data, ProductDTO.class);
            product = ICreateProductSagaInMapper.toProduct(input);
            product.setOwner(sagaId);
            product.setCountermeasure(CounterMeasure.BLOCKED); //  blocked
            createProductSaga.setSagaId(sagaId);
            createProductSaga.setProductId(product.getId());
            commandProduct.createProduct(product);
            createProductSaga.setSagaStatus(SagaStatus.PENDING);
            commandCreateProductSagaRepository.save(createProductSaga);
        }catch (Exception e){
            if(sagaId != null) createProductSaga.setSagaId(sagaId);
            if(product != null) createProductSaga.setProductId(product.getId());
            createProductSaga.setSagaStatus(SagaStatus.ERROR);
            commandCreateProductSagaRepository.save(createProductSaga);
            throw e;
        }
    }
}
