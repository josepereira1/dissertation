package com.example.ecommerce.saga.inbound.messaging.sagas.participants.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandOrderRabbitMQConfig {

    @Value("${co.publisher.exchange.name}")
    private String coExchange;

    @Value("${saga.routing.key}")
    private String sagaRoutingKey;

    @Bean(name = "coExchange")
    TopicExchange exchange() {
        return new TopicExchange(coExchange);
    }

    @Bean(name = "coBinding")
    Binding binding(Queue queue, TopicExchange coExchange) {
        return BindingBuilder.bind(queue).to(coExchange).with(sagaRoutingKey);
    }
}
