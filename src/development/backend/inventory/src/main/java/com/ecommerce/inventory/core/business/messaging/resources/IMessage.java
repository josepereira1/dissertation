package com.ecommerce.inventory.core.business.messaging.resources;

import com.google.gson.JsonElement;

public interface IMessage {
    String getOwner();
    void setOwner(String owner);
    String getExchange();
    void setExchange(String exchange);
    String getRouting();
    void setRouting(String routing);
    MessageType getType();
    void setType(MessageType type);
    MessageStatus getStatus();
    void setStatus(MessageStatus status);
    Integer getCode();
    void setCode(Integer code);
    String getMessage();
    void setMessage(String message);
    String getMethod();
    void setMethod(String method);
    JsonElement getMetadata();
    void setMetadata(JsonElement metadata);
    JsonElement getData();
    void setData(JsonElement data);
}
