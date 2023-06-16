package com.ecommerce.inventory.core.business.saga.sagas.createproduct.features;

import com.ecommerce.inventory.core.business.logic.product.command.CommandProduct;
import com.ecommerce.inventory.core.business.logs.ILogs;
import com.ecommerce.inventory.core.business.messaging.resources.IMessage;
import com.ecommerce.inventory.core.business.messaging.resources.MessageStatus;
import com.ecommerce.inventory.core.business.resources.CounterMeasure;
import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.business.saga.sagas.createproduct.mappers.in.ICreateProductSagaInMapper;
import com.ecommerce.inventory.core.business.saga.sagas.createproduct.mappers.in.dtos.ProductDTO;
import com.ecommerce.inventory.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.inventory.core.business.saga.utils.CommonSaga;
import com.ecommerce.inventory.core.business.saga.utils.resources.SagaStatus;
import com.ecommerce.inventory.core.ports.out.messaging.saga.ISagaOrchestrator;
import com.ecommerce.inventory.core.ports.out.repository.createproductsaga.ICommandCreateProductSagaRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateProductSagaLocalTransaction {

    @Autowired
    private ILogs logs;

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandCreateProductSagaRepository commandCreateProductSagaRepository;

    @Autowired
    private CommandProduct commandProduct;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    public void callLocalTransaction(IMessage message){
        try{
            localTransaction(message);
        }catch (Exception e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,500, e.getMessage() != null ? e.getMessage() : "Internal server error.", message.getMetadata(), message.getData());
            return;
        }
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS,200, "", message.getMetadata(), message.getData());
    }

    @Transactional(rollbackFor = Exception.class)
    public void localTransaction(IMessage message) throws Exception {
        JsonElement data = message.getData();
        String sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
        ProductDTO input = gson.fromJson(data, ProductDTO.class);
        Product product = ICreateProductSagaInMapper.toProduct(input);
        product.setOwner(sagaId);
        product.setCountermeasure(CounterMeasure.BLOCKED); //  BLOCKED
        CreateProductSaga createProductSaga = new CreateProductSaga();
        createProductSaga.setSagaId(sagaId);
        createProductSaga.setProductId(product.getId());
        commandProduct.createProduct(product);
        createProductSaga.setSagaStatus(SagaStatus.PENDING);
        commandCreateProductSagaRepository.save(createProductSaga);
    }
}
