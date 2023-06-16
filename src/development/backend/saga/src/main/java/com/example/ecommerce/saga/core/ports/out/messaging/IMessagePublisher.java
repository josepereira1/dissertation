package com.example.ecommerce.saga.core.ports.out.messaging;

import com.example.ecommerce.saga.core.business.messaging.resources.MessageStatus;
import com.example.ecommerce.saga.core.business.messaging.resources.MessageType;

public interface IMessagePublisher {
    String getExchange();
    void publish(String exchange, String routing, MessageType type, MessageStatus status, Integer code, String message, String method, Object metadata, Object jsonData);
}
