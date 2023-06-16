package com.ecommerce.qp.core.business.saga.sagas.createorder.features;

import com.ecommerce.qp.core.business.messaging.resources.IMessage;
import com.ecommerce.qp.core.business.messaging.resources.MessageStatus;
import com.ecommerce.qp.core.ports.out.saga.ISagaOrchestrator;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateOrderSagaCompensatingTransaction {

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    @Transactional
    public void callCompensatingTransaction(IMessage message){
        compensatingTransaction(message);
    }

    public void compensatingTransaction(IMessage message){
        JsonObject data = message.getData().getAsJsonObject();
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS, 200, "", message.getMetadata(), data);
    }
}
