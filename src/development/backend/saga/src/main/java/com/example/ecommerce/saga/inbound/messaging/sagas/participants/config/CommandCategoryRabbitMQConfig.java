package com.example.ecommerce.saga.inbound.messaging.sagas.participants.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandCategoryRabbitMQConfig {

    @Value("${cc.publisher.exchange.name}")
    private String ccExchange;

    @Value("${saga.routing.key}")
    private String sagaRoutingKey;

    @Bean(name = "ccExchange")
    TopicExchange exchange() {
        return new TopicExchange(ccExchange);
    }

    @Bean(name = "ccBinding")
    Binding binding(Queue queue, TopicExchange ccExchange) {
        return BindingBuilder.bind(queue).to(ccExchange).with(sagaRoutingKey);
    }
}
