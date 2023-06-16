package com.ecommerce.co.inbound.messaging.sagas.createorder;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateOrderSagaRabbitMQConfig {

    @Value("${saga.publisher.exchange.name}")
    public String exchange;

    @Value("${create.order.saga.0.co.routing.key}")
    public String localTransactionRoutingKey;

    @Bean(name = "createOrderSagaLocalAndCompensatingExchange")
    TopicExchange exchange1() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "createOrderSagaLocalAndCompensatingBinding")
    Binding binding1(Queue queue, TopicExchange createOrderSagaLocalAndCompensatingExchange) {
        return BindingBuilder.bind(queue).to(createOrderSagaLocalAndCompensatingExchange).with(localTransactionRoutingKey);
    }

    @Value("${create.order.saga.0.commit.routing.key}")
    public String commitRoutingKey;

    @Bean(name = "createOrderSagaCommitExchange")
    TopicExchange exchange2() {
        return new TopicExchange(exchange);
    }

    @Bean(name = "createOrderSagaCommitBinding")
    Binding binding2(Queue queue, TopicExchange createOrderSagaCommitExchange) {
        return BindingBuilder.bind(queue).to(createOrderSagaCommitExchange).with(commitRoutingKey);
    }
}
