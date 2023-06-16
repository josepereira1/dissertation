package com.example.ecommerce.saga.inbound.messaging.sagas.participants.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryRabbitMQConfig {

    @Value("${inventory.publisher.exchange.name}")
    private String inventoryExchange;

    @Value("${saga.routing.key}")
    private String sagaRoutingKey;

    @Bean(name = "inventoryExchange")
    TopicExchange exchange() {
        return new TopicExchange(inventoryExchange);
    }

    @Bean(name = "inventoryBinding")
    Binding binding(Queue queue, TopicExchange inventoryExchange) {
        return BindingBuilder.bind(queue).to(inventoryExchange).with(sagaRoutingKey);
    }
}
