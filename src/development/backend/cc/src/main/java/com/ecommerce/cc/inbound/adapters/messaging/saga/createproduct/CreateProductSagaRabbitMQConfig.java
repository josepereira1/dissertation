package com.ecommerce.cc.inbound.adapters.messaging.saga.createproduct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateProductSagaRabbitMQConfig {

    @Value("${saga.publisher.exchange.name}")
    private String exchange;

    @Value("${create.product.saga.0.cc.routing.key}")
    private String localAndCompensatingTransactionsRoutingKey;

    @Bean(name = "createProductSagaLocalAndCompensableTransactionExchange")
    TopicExchange exchange1() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "createProductSagaLocalAndCompensableTransactionBinding")
    Binding binding1(Queue queue, TopicExchange createProductSagaLocalAndCompensableTransactionExchange) {
        return BindingBuilder.bind(queue).to(createProductSagaLocalAndCompensableTransactionExchange).with(localAndCompensatingTransactionsRoutingKey);
    }

    @Value("${create.product.saga.0.commit.routing.key}")
    private String commitRoutingKey;

    @Bean(name = "createProductSagaCommitExchange")
    TopicExchange exchange2() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "createProductSagaCommitBinding")
    Binding binding2(Queue queue, TopicExchange createProductSagaCommitExchange) {
        return BindingBuilder.bind(queue).to(createProductSagaCommitExchange).with(commitRoutingKey);
    }
}
