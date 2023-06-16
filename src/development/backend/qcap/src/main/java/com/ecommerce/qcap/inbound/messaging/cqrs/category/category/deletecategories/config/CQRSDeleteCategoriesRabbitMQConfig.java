package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.deletecategories.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CQRSDeleteCategoriesRabbitMQConfig {
    @Value("${cc.publisher.exchange.name}")
    private String exchange;

    @Value("${cqrs.0.delete.categories.routing.key}")
    private String routingKey;

    @Bean(name = "cqrsDeleteCategoriesExchange")
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "cqrsDeleteCategoriesBinding")
    Binding binding(Queue queue, TopicExchange cqrsDeleteCategoriesExchange) {
        return BindingBuilder.bind(queue).to(cqrsDeleteCategoriesExchange).with(routingKey);
    }
}
