package com.ecommerce.qct.core.business.messaging.mappers;

import com.ecommerce.qct.core.business.messaging.factory.IMessageFactory;
import com.ecommerce.qct.core.business.messaging.resources.IMessage;
import com.ecommerce.qct.core.business.messaging.resources.Message;
import com.ecommerce.qct.core.business.messaging.resources.MessageStatus;
import com.ecommerce.qct.core.business.messaging.resources.MessageType;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.Type;

public class MessageDeserializer implements JsonDeserializer<Message> {

    @Autowired
    private IMessageFactory messageFactory;

    @Override
    public Message deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        IMessage message = messageFactory.factory();
        JsonObject jo = jsonElement.getAsJsonObject();
        if(jo.has("owner")) message.setOwner(jo.get("owner").getAsString());
        if(jo.has("exchange")) message.setExchange(jo.get("exchange").getAsString());
        if(jo.has("routing")) message.setRouting(jo.get("routing").getAsString());
        if(jo.has("type")) message.setType(MessageType.valueOf(jo.get("type").getAsString()));
        if(jo.has("status")) message.setStatus(MessageStatus.valueOf(jo.get("status").getAsString()));
        if(jo.has("code")) message.setCode(jo.get("code").getAsInt());
        if(jo.has("message")) message.setMessage(jo.get("message").getAsString());
        if(jo.has("method")) message.setMethod(jo.get("method").getAsString());
        if(jo.has("metadata")) message.setData(jo.get("metadata").getAsJsonObject());
        if(jo.has("data")) message.setData(jo.get("data").getAsJsonObject());
        return (Message) message;
    }
}
