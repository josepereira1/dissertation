package com.example.ecommerce.saga.core.business.messaging.factory;

import com.example.ecommerce.saga.core.business.messaging.resources.IMessage;
import com.example.ecommerce.saga.core.business.messaging.resources.MessageStatus;
import com.example.ecommerce.saga.core.business.messaging.resources.MessageType;
import com.google.gson.JsonElement;

public interface IMessageFactory {
    IMessage factory();
    IMessage factory(String owner, String exchange, String routing, MessageType type, MessageStatus status, Integer code, String message, String method, JsonElement metadata, JsonElement data);
}
