package com.ecommerce.qp.inbound.messaging.cqrs.product.create.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CQRSCreateProductRabbitMQConfig {

    @Value("${cp.publisher.exchange.name}")
    private String exchange;

    @Value("${cqrs.0.create.product.routing.key}")
    private String routingKey;

    @Bean(name = "cqrsCreateProductExchange")
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "cqrsCreateProductBinding")
    Binding binding(Queue queue, TopicExchange cqrsCreateProductExchange) {
        return BindingBuilder.bind(queue).to(cqrsCreateProductExchange).with(routingKey);
    }
}
