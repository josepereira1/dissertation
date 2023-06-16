package com.example.ecommerce.saga.inbound.messaging.sagas.participants.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingCartRabbitMQConfig {

    @Value("${sc.publisher.exchange.name}")
    private String qpExchange;

    @Value("${saga.routing.key}")
    private String sagaRoutingKey;

    @Bean(name = "scExchange")
    TopicExchange exchange() {
        return new TopicExchange(qpExchange);
    }

    @Bean(name = "scBinding")
    Binding binding(Queue queue, TopicExchange scExchange) {
        return BindingBuilder.bind(queue).to(scExchange).with(sagaRoutingKey);
    }
}
