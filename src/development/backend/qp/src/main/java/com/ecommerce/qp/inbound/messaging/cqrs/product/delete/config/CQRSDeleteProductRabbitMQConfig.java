package com.ecommerce.qp.inbound.messaging.cqrs.product.delete.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CQRSDeleteProductRabbitMQConfig {

    @Value("${cp.publisher.exchange.name}")
    private String exchange;

    @Value("${cqrs.0.delete.product.routing.key}")
    private String routingKey;

    @Bean(name = "cqrsDeleteProductExchange")
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "cqrsDeleteProductBinding")
    Binding binding(Queue queue, TopicExchange cqrsDeleteProductExchange) {
        return BindingBuilder.bind(queue).to(cqrsDeleteProductExchange).with(routingKey);
    }
}
