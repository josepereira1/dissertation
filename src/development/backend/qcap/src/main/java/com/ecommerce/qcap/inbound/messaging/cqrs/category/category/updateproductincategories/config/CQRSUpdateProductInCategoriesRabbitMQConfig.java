package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.updateproductincategories.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CQRSUpdateProductInCategoriesRabbitMQConfig {

    @Value("${cc.publisher.exchange.name}")
    private String exchange;

    @Value("${cqrs.0.update.product.in.categories.routing.key}")
    private String routingKey;

    @Bean(name = "cqrsUpdateProductInCategoriesExchange")
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "cqrsUpdateProductInCategoriesBinding")
    Binding binding(Queue queue, TopicExchange cqrsUpdateProductInCategoriesExchange) {
        return BindingBuilder.bind(queue).to(cqrsUpdateProductInCategoriesExchange).with(routingKey);
    }
}
