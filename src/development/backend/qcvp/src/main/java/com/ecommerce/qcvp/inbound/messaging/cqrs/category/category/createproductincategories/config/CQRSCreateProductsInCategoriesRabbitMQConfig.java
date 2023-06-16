package com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.createproductincategories.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CQRSCreateProductsInCategoriesRabbitMQConfig {

    @Value("${cc.publisher.exchange.name}")
    private String exchange;

    @Value("${cqrs.0.create.product.in.categories.routing.key}")
    private String routingKey;

    @Bean(name = "cqrsCreateProductInCategoriesExchange")
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "cqrsCreateProductInCategoriesBinding")
    Binding binding(Queue queue, TopicExchange cqrsCreateProductInCategoriesExchange) {
        return BindingBuilder.bind(queue).to(cqrsCreateProductInCategoriesExchange).with(routingKey);
    }
}
