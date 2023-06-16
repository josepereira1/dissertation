package com.ecommerce.inventory.core.business.saga.sagas.createorder.features;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.inventory.core.business.exceptions.product.NoStockAvailableException;
import com.ecommerce.inventory.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.inventory.core.business.logic.product.command.CommandProduct;
import com.ecommerce.inventory.core.business.logs.ILogs;
import com.ecommerce.inventory.core.business.messaging.resources.IMessage;
import com.ecommerce.inventory.core.business.messaging.resources.MessageStatus;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.mappers.in.ICreateOrderSagaOrderInMapper;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.mappers.in.dtos.OrderDTO;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.inventory.core.business.saga.utils.CommonSaga;
import com.ecommerce.inventory.core.business.saga.utils.resources.SagaStatus;
import com.ecommerce.inventory.core.ports.out.messaging.saga.ISagaOrchestrator;
import com.ecommerce.inventory.core.ports.out.repository.createordersaga.ICommandCreateOrderSagaRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
public class CreateOrderSagaLocalTransaction {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private CommandProduct commandProduct;

    @Autowired
    private ICommandCreateOrderSagaRepository commandCreateOrderSagaRepository;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    @Autowired
    private ILogs logs;

    public void callLocalTransaction(IMessage message){
        try{
            localTransaction(message);
        } catch (NoStockAvailableException e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            String errorMessage = e.getMessage() != null ? e.getMessage() : "No stock available.";
            Integer errorCode = 404;
            message.getData().getAsJsonObject().addProperty("errorMessage", errorMessage);
            message.getData().getAsJsonObject().addProperty("errorCode", errorCode);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,errorCode, errorMessage, message.getMetadata(), message.getData());
            return;
        } catch (ProductWithThatIdNotExistException e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            String errorMessage = e.getMessage() != null ? e.getMessage() : "Product not exists.";
            Integer errorCode = 404;
            message.getData().getAsJsonObject().addProperty("errorMessage", errorMessage);
            message.getData().getAsJsonObject().addProperty("errorCode", errorCode);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,errorCode, errorMessage, message.getMetadata(), message.getData());
            return;
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            String errorMessage = e.getMessage() != null ? e.getMessage() : "Internal server error.";
            Integer errorCode = 500;
            message.getData().getAsJsonObject().addProperty("errorMessage", errorMessage);
            message.getData().getAsJsonObject().addProperty("errorCode", errorCode);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,errorCode, errorMessage, message.getMetadata(), message.getData());
            return;
        }
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS,200, "", message.getMetadata(), message.getData());
    }

    @Transactional(rollbackFor = Exception.class)
    public void localTransaction(IMessage message) throws EcommerceBusinessLogicException {
        CreateOrderSaga createOrderSaga = new CreateOrderSaga(); String sagaId = null;
        try {
            JsonElement data = message.getData();
            sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
            OrderDTO input = gson.fromJson(data, OrderDTO.class);
            createOrderSaga.setSagaId(sagaId);
            createOrderSaga.setProductsBackup(input.getProducts().stream().map(ICreateOrderSagaOrderInMapper::toCreateOrderSagaProductBackup).collect(Collectors.toSet()));
            commandProduct.removeStock(input.getProducts().stream().map(ICreateOrderSagaOrderInMapper::toProduct).collect(Collectors.toList()));
            createOrderSaga.setSagaStatus(SagaStatus.PENDING);
            commandCreateOrderSagaRepository.save(createOrderSaga);
        }catch (Exception e){
            if(sagaId != null) createOrderSaga.setSagaId(sagaId);
            createOrderSaga.setSagaStatus(SagaStatus.ERROR);
            commandCreateOrderSagaRepository.save(createOrderSaga);
            throw e;
        }
    }
}
