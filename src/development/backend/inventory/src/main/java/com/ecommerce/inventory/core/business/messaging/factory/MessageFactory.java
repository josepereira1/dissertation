package com.ecommerce.inventory.core.business.messaging.factory;

import com.ecommerce.inventory.core.business.messaging.resources.IMessage;
import com.ecommerce.inventory.core.business.messaging.resources.Message;
import com.ecommerce.inventory.core.business.messaging.resources.MessageStatus;
import com.ecommerce.inventory.core.business.messaging.resources.MessageType;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Service;

@Service
public class MessageFactory implements IMessageFactory {

    @Override
    public IMessage factory() {
        return new Message();
    }

    @Override
    public IMessage factory(String owner, String exchange, String routing, MessageType type, MessageStatus status, Integer code, String message, String method, JsonElement metadata, JsonElement data) {
        IMessage tmp = factory();
        tmp.setOwner(owner);
        tmp.setExchange(exchange);
        tmp.setRouting(routing);
        tmp.setType(type);
        tmp.setStatus(status);
        tmp.setCode(code);
        tmp.setMessage(message);
        tmp.setMethod(method);
        tmp.setMetadata(metadata);
        tmp.setData(data);
        return tmp;
    }
}
