package com.example.ecommerce.saga.inbound.messaging.sagas.participants.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandProductRabbitMQConfig {

    @Value("${cp.publisher.exchange.name}")
    private String cpExchange;

    @Value("${saga.routing.key}")
    private String sagaRoutingKey;

    @Bean(name = "cpExchange")
    TopicExchange exchange() {
        return new TopicExchange(cpExchange);
    }

    @Bean(name = "cpBinding")
    Binding binding(Queue queue, TopicExchange cpExchange) {
        return BindingBuilder.bind(queue).to(cpExchange).with(sagaRoutingKey);
    }
}
