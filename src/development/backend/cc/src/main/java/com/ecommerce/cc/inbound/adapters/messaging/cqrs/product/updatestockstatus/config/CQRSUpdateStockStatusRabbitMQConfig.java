package com.ecommerce.cc.inbound.adapters.messaging.cqrs.product.updatestockstatus.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CQRSUpdateStockStatusRabbitMQConfig {

    @Value("${inventory.publisher.exchange.name}")
    private String exchange;

    @Value("${cqrs.0.update.stockStatus.routing.key}")
    private String routingKey;

    @Bean(name = "cqrsUpdateStockStatusExchange")
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "cqrsUpdateStockStatusBinding")
    Binding binding(Queue queue, TopicExchange cqrsUpdateStockStatusExchange) {
        return BindingBuilder.bind(queue).to(cqrsUpdateStockStatusExchange).with(routingKey);
    }
}
