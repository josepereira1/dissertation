package com.ecommerce.qcap.core.business.messaging.resources;

import com.google.gson.JsonElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Message implements IMessage, Serializable {
    private String owner;
    private String exchange;
    private String routing;
    private MessageType type;
    private MessageStatus status;
    private Integer code;
    private String message;
    private String method;
    private JsonElement metadata;
    private JsonElement data;
}
