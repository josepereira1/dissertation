package com.ecommerce.qct.inbound.messaging.cqrs.category.tree.updatecategorytree.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CQRSUpdateCategoryTreeRabbitMQConfig {

    @Value("${cc.publisher.exchange.name}")
    private String exchange;

    @Value("${cqrs.0.update.category.tree.routing.key}")
    private String routingKey;

    @Bean(name = "cqrsUpdateCategoryTreeExchange")
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "cqrsUpdateCategoryTreeBinding")
    Binding binding(Queue queue, TopicExchange cqrsUpdateCategoryTreeExchange) {
        return BindingBuilder.bind(queue).to(cqrsUpdateCategoryTreeExchange).with(routingKey);
    }
}
