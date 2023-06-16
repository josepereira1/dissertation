package com.ecommerce.sc.core.business.messaging.factory;

import com.ecommerce.sc.core.business.messaging.resources.IMessage;
import com.ecommerce.sc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.sc.core.business.messaging.resources.MessageType;
import com.google.gson.JsonElement;

public interface IMessageFactory {
    IMessage factory();
    IMessage factory(String owner, String exchange, String routing, MessageType type, MessageStatus status, Integer code, String message, String method, JsonElement metadata, JsonElement data);
}
