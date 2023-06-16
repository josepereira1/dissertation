package com.ecommerce.co.outbound.messaging.saga;

import com.ecommerce.co.core.business.messaging.resources.MessageStatus;
import com.ecommerce.co.core.business.messaging.resources.MessageType;
import com.ecommerce.co.core.ports.out.saga.ISagaOrchestrator;
import com.ecommerce.co.outbound.messaging.producer.MessagePublisher;
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
