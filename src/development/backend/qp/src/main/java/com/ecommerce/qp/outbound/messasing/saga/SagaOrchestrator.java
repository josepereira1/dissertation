package com.ecommerce.qp.outbound.messasing.saga;

import com.ecommerce.qp.core.business.messaging.resources.MessageStatus;
import com.ecommerce.qp.core.business.messaging.resources.MessageType;
import com.ecommerce.qp.core.ports.out.saga.ISagaOrchestrator;
import com.ecommerce.qp.outbound.messasing.producer.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SagaOrchestrator implements ISagaOrchestrator {

    @Value("${saga.method}")
    private String sagaMethod;

    @Autowired
    private MessagePublisher messagePublisher;

    @Override
    public void replyToOrchestrator(String orchestratorName, MessageStatus messageStatus, Integer code, String message, Object metadata, Object data) {
        messagePublisher.publish(messagePublisher.getExchange(), orchestratorName, MessageType.REPLY, messageStatus, code != null ? code : -1, message != null ? message : "", sagaMethod, metadata, data);
    }
}
