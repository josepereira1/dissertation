package com.ecommerce.cp.outbound.messaging.messaging.producer;

import com.ecommerce.cp.core.business.logs.ILogs;
import com.ecommerce.cp.core.business.messaging.factory.IMessageFactory;
import com.ecommerce.cp.core.business.messaging.logic.IMessagingBusiness;
import com.ecommerce.cp.core.business.messaging.resources.IMessage;
import com.ecommerce.cp.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cp.core.business.messaging.resources.MessageType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    @Value("${service}")
    private String service;

    @Value("${publisher.exchange.name}")
    private String exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private IMessagingBusiness messagingBusiness;

    @Autowired
    private ILogs logs;

    @Autowired
    private IMessageFactory messageFactory;

    public void publish(String exchange, String routing, MessageType type, MessageStatus status, Integer code, String message, String method, Object metadata, Object data){
        IMessage res = messageFactory.factory(service.toLowerCase(), exchange, routing, type, status, code, message, method, gson.toJsonTree(metadata != null && !metadata.equals("") ? metadata : new Object()), gson.toJsonTree(data != null && !data.equals("") ? data : new Object()));
        logs.logInfo("[MESSAGE_SENT] exchange: " + exchange + " - routing: " + routing + " - method: " + method + " - message: " + res);
        rabbitTemplate.convertAndSend(exchange,routing, messagingBusiness.toJson(res));
    }

    public String getExchange(){
        return exchange;
    }
}
