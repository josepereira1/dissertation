package com.example.ecommerce.saga.inbound.messaging.sagas.messaging.consumer.config;

import com.example.ecommerce.saga.core.ports.in.coordination.ISagaCoordination;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaRabbitMqConfig {

    @Value("${subscriber.queue.name}")
    private String consumerQueueName;

    @Bean(name = "queue")
    Queue queue() {
        return new Queue(consumerQueueName, true);
    }

    @Bean(name = "container")
    SimpleMessageListenerContainer createContainer(ConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter1) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(consumerQueueName);
        container.setMessageListener(listenerAdapter1);
        container.setConcurrentConsumers(400);
        container.setMaxConcurrentConsumers(400);
        return container;
    }

    @Bean(name = "listenerAdapter1")
    MessageListenerAdapter listenerAdapter1(ISagaCoordination receiver) {
        return new MessageListenerAdapter(receiver, "handleMessage");
    }
}
