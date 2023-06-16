package com.ecommerce.sc.core.business.sagas.createorder.features;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.sc.core.business.exceptions.shoppingcart.ShoppingCartNotExistsException;
import com.ecommerce.sc.core.business.exceptions.util.ParametersInFaultException;
import com.ecommerce.sc.core.business.logic.shoppingcart.query.QueryShoppingCart;
import com.ecommerce.sc.core.business.logs.ILogs;
import com.ecommerce.sc.core.business.messaging.resources.IMessage;
import com.ecommerce.sc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import com.ecommerce.sc.core.business.sagas.createorder.mappers.in.dtos.ClientDTO;
import com.ecommerce.sc.core.business.sagas.createorder.mappers.out.ICreateOrderSagaOutMapper;
import com.ecommerce.sc.core.business.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.sc.core.business.sagas.utils.CommonSaga;
import com.ecommerce.sc.core.business.sagas.utils.resources.SagaMetadata;
import com.ecommerce.sc.core.business.sagas.utils.resources.SagaStatus;
import com.ecommerce.sc.core.business.security.Authentication;
import com.ecommerce.sc.core.business.security.Role;
import com.ecommerce.sc.core.ports.out.repository.createordersaga.ICommandCreateOrderSagaRepository;
import com.ecommerce.sc.core.ports.out.saga.ISagaOrchestrator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateOrderSagaLocalTransaction {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandCreateOrderSagaRepository commandCreateOrderSagaRepository;

    @Autowired
    private QueryShoppingCart queryShoppingCart;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    @Autowired
    private Authentication authentication;

    @Autowired
    private ILogs logs;

    public void callLocalTransaction(IMessage message){
        try {
            localTransaction(message);
        } catch (ShoppingCartNotExistsException e) {
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR, 404, e.getMessage() != null ? e.getMessage() : "Shopping cart not exists.", message.getMetadata(),  message.getData());
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
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,500, e.getMessage() != null ? e.getMessage() : "Parameters in fault.", message.getMetadata(), message.getData());
            return;
        }
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS, 200,"", message.getMetadata(), message.getData());
    }

    @Transactional(rollbackFor = Exception.class)
    public void localTransaction(IMessage message) throws EcommerceBusinessLogicException {
        CreateOrderSaga createOrderSaga = new CreateOrderSaga(); SagaMetadata sagaMetadata = null; String clientId = null;
        try {
            JsonElement data = message.getData();
            sagaMetadata = CommonSaga.getSagaMetadata(message.getMetadata());
            ClientDTO input = gson.fromJson(data, ClientDTO.class);

            //  AUTHORIZATION
            Claims tokenClaims = authentication.authenticateAndGetClaims(sagaMetadata.getToken());
            if (tokenClaims.get("role") != null && (tokenClaims.get("role").equals(Role.MANAGER.name())) || tokenClaims.get("role").equals(Role.ADMIN.name())) { //  EMPLOYEE & ADMIN
                if (input.getClientId() == null)
                    throw ParametersInFaultException.builder().parameters("clientId").build();
                clientId = input.getClientId();
            } else if (tokenClaims.get("role") != null && (tokenClaims.get("role").equals(Role.CONSUMER.name()))) {                                             //  CLIENT
                clientId = tokenClaims.getSubject();
            } else {                                                                                                                                            //  OTHERS
                throw UnauthorizedException.builder().build();
            }

            Set<ShoppingCartAndProduct> products = queryShoppingCart.readShoppingCartProducts(clientId);
            data.getAsJsonObject().add("products", gson.toJsonTree(products.stream().map(ICreateOrderSagaOutMapper::toProductDTO).collect(Collectors.toList())));
            createOrderSaga.setSagaId(sagaMetadata.getSagaId());
            createOrderSaga.setShoppingCartId(clientId);
            createOrderSaga.setSagaStatus(SagaStatus.PENDING);
            commandCreateOrderSagaRepository.save(createOrderSaga);
        }catch (Exception e){
            if(sagaMetadata != null && sagaMetadata.getSagaId() != null) createOrderSaga.setSagaId(sagaMetadata.getSagaId());
            if(clientId != null) createOrderSaga.setShoppingCartId(clientId);
            createOrderSaga.setSagaStatus(SagaStatus.ERROR);
            commandCreateOrderSagaRepository.save(createOrderSaga);
            throw e;
        }
    }
}
