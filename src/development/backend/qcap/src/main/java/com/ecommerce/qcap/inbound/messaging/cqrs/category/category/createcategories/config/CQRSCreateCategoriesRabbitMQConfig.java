package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createcategories.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CQRSCreateCategoriesRabbitMQConfig {

    @Value("${cc.publisher.exchange.name}")
    private String exchange;

    @Value("${cqrs.0.create.categories.routing.key}")
    private String routingKey;

    @Bean(name = "cqrsCreateCategoriesExchange")
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "cqrsCreateCategoriesBinding")
    Binding binding(Queue queue, TopicExchange cqrsCreateCategoriesExchange) {
        return BindingBuilder.bind(queue).to(cqrsCreateCategoriesExchange).with(routingKey);
    }
}
