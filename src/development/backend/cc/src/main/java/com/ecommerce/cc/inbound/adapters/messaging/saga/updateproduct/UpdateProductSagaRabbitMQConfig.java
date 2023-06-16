package com.ecommerce.cc.inbound.adapters.messaging.saga.updateproduct;

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
    private String exchange;

    @Value("${update.product.saga.0.cc.routing.key}")
    private String localAndCompensatingTransactionsRoutingKey;

    @Bean(name = "updateProductSagaLocalAndCompensatingTransactionExchange")
    TopicExchange exchange1() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "updateProductSagaLocalAndCompensatingTransactionBinding")
    Binding binding1(Queue queue, TopicExchange updateProductSagaLocalAndCompensatingTransactionExchange) {
        return BindingBuilder.bind(queue).to(updateProductSagaLocalAndCompensatingTransactionExchange).with(localAndCompensatingTransactionsRoutingKey);
    }

    @Value("${update.product.saga.0.commit.routing.key}")
    private String commitRoutingKey;

    @Bean(name = "updateProductSagaCommitExchange")
    TopicExchange exchange2() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "updateProductSagaCommitBinding")
    Binding binding2(Queue queue, TopicExchange updateProductSagaCommitExchange) {
        return BindingBuilder.bind(queue).to(updateProductSagaCommitExchange).with(commitRoutingKey);
    }
}
