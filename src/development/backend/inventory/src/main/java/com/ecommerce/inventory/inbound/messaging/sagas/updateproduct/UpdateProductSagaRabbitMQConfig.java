package com.ecommerce.inventory.inbound.messaging.sagas.updateproduct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateProductSagaRabbitMQConfig {

    @Value("${saga.publisher.exchange.name}")
    public String exchange;

    @Value("${update.product.saga.0.inventory.routing.key}")
    public String localTransactionRoutingKey;

    @Bean(name = "updateProductSagaLocalAndCompensatingExchange")
    TopicExchange exchange1() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "updateProductSagaLocalAndCompensatingBinding")
    Binding binding1(Queue queue, TopicExchange updateProductSagaLocalAndCompensatingExchange) {
        return BindingBuilder.bind(queue).to(updateProductSagaLocalAndCompensatingExchange).with(localTransactionRoutingKey);
    }

    @Value("${update.product.saga.0.commit.routing.key}")
    public String commitRoutingKey;

    @Bean(name = "updateProductSagaCommitExchange")
    TopicExchange exchange2() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "updateProductSagaCommitBinding")
    Binding binding2(Queue queue, TopicExchange updateProductSagaCommitExchange) {
        return BindingBuilder.bind(queue).to(updateProductSagaCommitExchange).with(commitRoutingKey);
    }
}
