package com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.updatecategoryname.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CQRSUpdateCategoryNameRabbitMQConfig {

    @Value("${cc.publisher.exchange.name}")
    private String exchange;

    @Value("${cqrs.0.update.category.name.routing.key}")
    private String routingKey;

    @Bean(name = "cqrsUpdateCategoryNameExchange")
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "cqrsUpdateCategoryNameBinding")
    Binding binding(Queue queue, TopicExchange cqrsUpdateCategoryNameExchange) {
        return BindingBuilder.bind(queue).to(cqrsUpdateCategoryNameExchange).with(routingKey);
    }
}
