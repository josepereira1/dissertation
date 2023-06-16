package com.ecommerce.cp.inbound.messaging.sagas.createproduct;

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
    public String exchange;

    @Value("${create.product.saga.0.cp.routing.key}")
    public String localTransactionRoutingKey;

    @Bean(name = "createProductSagaLocalAndCompensatingExchange")
    TopicExchange exchange1() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "createProductSagaLocalAndCompensatingBinding")
    Binding binding1(Queue queue, TopicExchange createProductSagaLocalAndCompensatingExchange) {
        return BindingBuilder.bind(queue).to(createProductSagaLocalAndCompensatingExchange).with(localTransactionRoutingKey);
    }

    @Value("${create.product.saga.0.commit.routing.key}")
    public String commitRoutingKey;

    @Bean(name = "createProductSagaCommitExchange")
    TopicExchange exchange2() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "createProductSagaCommitBinding")
    Binding binding2(Queue queue, TopicExchange createProductSagaCommitExchange) {
        return BindingBuilder.bind(queue).to(createProductSagaCommitExchange).with(commitRoutingKey);
    }
}
