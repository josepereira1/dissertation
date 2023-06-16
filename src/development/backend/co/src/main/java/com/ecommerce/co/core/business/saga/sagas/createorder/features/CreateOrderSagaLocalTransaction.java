package com.ecommerce.co.core.business.saga.sagas.createorder.features;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.co.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.co.core.business.exceptions.order.CantCreateOrderWithouProductsException;
import com.ecommerce.co.core.business.exceptions.util.ParametersInFaultException;
import com.ecommerce.co.core.business.logic.order.command.features.CreateOrder;
import com.ecommerce.co.core.business.logs.ILogs;
import com.ecommerce.co.core.business.messaging.resources.IMessage;
import com.ecommerce.co.core.business.messaging.resources.MessageStatus;
import com.ecommerce.co.core.business.resources.CounterMeasure;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.saga.sagas.createorder.mappers.in.order.dtos.CreateOrderInputDTO;
import com.ecommerce.co.core.business.saga.sagas.createorder.mappers.in.order.ICreateOrderSagaInMapper;
import com.ecommerce.co.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.co.core.business.saga.utils.CommonSaga;
import com.ecommerce.co.core.business.saga.utils.resources.SagaMetadata;
import com.ecommerce.co.core.business.saga.utils.resources.SagaStatus;
import com.ecommerce.co.core.business.security.Authentication;
import com.ecommerce.co.core.business.security.Role;
import com.ecommerce.co.core.ports.out.repository.createordersaga.ICommandCreateOrderSagaRepository;
import com.ecommerce.co.core.ports.out.saga.ISagaOrchestrator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateOrderSagaLocalTransaction {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private CreateOrder createOrder;

    @Autowired
    private ICommandCreateOrderSagaRepository commandCreateOrderSagaRepository;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    @Autowired
    private Authentication authentication;

    @Autowired
    private ILogs logs;

    public void callLocalTransaction(IMessage message){
        try{
            localTransaction(message);
        } catch (CantCreateOrderWithouProductsException e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,404, e.getMessage() != null ? e.getMessage() : "Some products not exists.", message.getMetadata(), message.getData());
            return;
        } catch (UnauthorizedException e) {
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,401, e.getMessage() != null ? e.getMessage() : "Unauthorized.", message.getMetadata(), message.getData());
            return;
        } catch (ParametersInFaultException e) {
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,400, e.getMessage() != null ? e.getMessage() : "Unauthorized.", message.getMetadata(), message.getData());
            return;
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,500, e.getMessage() != null ? e.getMessage() : "Internal server error.", message.getMetadata(), message.getData());
            return;
        }
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS, 200, "", message.getMetadata(), message.getData());
    }

    @Transactional(rollbackFor = Exception.class)
    public void localTransaction(IMessage message) throws EcommerceBusinessLogicException {
        CreateOrderSaga createOrderSaga = new CreateOrderSaga(); SagaMetadata sagaMetadata = null; Order order = null; String clientId;
        try {
            JsonElement data = message.getData();
            sagaMetadata = CommonSaga.getSagaMetadata(message.getMetadata());
            CreateOrderInputDTO input = gson.fromJson(data, CreateOrderInputDTO.class);

            //  AUTHORIZATION
            Claims tokenClaims = authentication.authenticateAndGetClaims(sagaMetadata.getToken());
            if (tokenClaims.get("role") != null && (tokenClaims.get("role").equals(Role.MANAGER.name())) || tokenClaims.get("role").equals(Role.ADMIN.name())) { //  EMPLOYEE & ADMIN
                if (input.getClientId() == null)
                    throw ParametersInFaultException.builder().parameters("clientId").build();
                clientId = input.getClientId();
            } else if (tokenClaims.get("role") != null && (tokenClaims.get("role").equals(Role.CONSUMER.name()))) {                                                 //  CLIENT
                clientId = tokenClaims.getSubject();
            } else {                                                                                                                                            //  OTHERS
                throw UnauthorizedException.builder().build();
            }

            order = ICreateOrderSagaInMapper.toOrder(input);
            order.setClientId(clientId);
            order.setOwner(sagaMetadata.getSagaId());
            order.setCountermeasure(CounterMeasure.BLOCKED);
            order = createOrder.createOrder(order);
            createOrderSaga.setSagaId(sagaMetadata.getSagaId());
            createOrderSaga.setOrderId(order.getId());
            data.getAsJsonObject().addProperty("id", order.getId());
            createOrderSaga.setSagaStatus(SagaStatus.PENDING);
            commandCreateOrderSagaRepository.save(createOrderSaga);
        } catch (Exception e){
            createOrderSaga.setSagaId(sagaMetadata.getSagaId());
            if(order != null) createOrderSaga.setOrderId(order.getId());
            createOrderSaga.setSagaStatus(SagaStatus.ERROR);
            commandCreateOrderSagaRepository.save(createOrderSaga);

            throw e;
        }
    }
}
