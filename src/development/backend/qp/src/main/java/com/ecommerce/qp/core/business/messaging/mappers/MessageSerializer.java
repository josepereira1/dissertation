package com.ecommerce.qp.core.business.messaging.mappers;

import com.ecommerce.qp.core.business.messaging.resources.Message;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class MessageSerializer implements JsonSerializer<Message> {

    @Override
    public JsonElement serialize(Message message, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jo = new JsonObject();
        if(message.getOwner() != null && !message.getOwner().equals("")) jo.addProperty("owner", message.getExchange());
        if(message.getExchange() != null && !message.getExchange().equals("")) jo.addProperty("exchange", message.getExchange());
        if(message.getRouting() != null && !message.getRouting().equals("")) jo.addProperty("routing", message.getRouting());
        if(message.getType() != null && !message.getType().equals("")) jo.addProperty("type", message.getType().name());
        if(message.getStatus() != null && !message.getStatus().equals("")) jo.addProperty("status", message.getStatus().name());
        if(message.getCode() != null) jo.addProperty("code", message.getCode());
        if(message.getMessage() != null && !message.getMessage().equals("")) jo.addProperty("message", message.getMessage());
        if(message.getMethod() != null && !message.getMethod().equals("")) jo.addProperty("method", message.getMethod());
        if(message.getMetadata() != null) jo.add("metadata", message.getMetadata());
        if(message.getData() != null) jo.add("data", message.getData());
        return jo;
    }
}
