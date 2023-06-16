package com.ecommerce.qp.inbound.messaging.messaging.consumer.config;

import com.ecommerce.qp.inbound.messaging.messaging.consumer.MessageHandler;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${subscriber.queue.name}")
    private String consumerQueueName;

    @Value("${replica}")
    private String replica;

    @Bean(name = "queue")
    Queue queue() {
        return new Queue(consumerQueueName + "." + replica, true);
    }

    @Bean(name = "container")
    SimpleMessageListenerContainer createContainer(ConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(consumerQueueName + "." + replica);
        container.setMessageListener(listenerAdapter);
        container.setConcurrentConsumers(200);
        container.setMaxConcurrentConsumers(200);
        return container;
    }

    @Bean(name = "listenerAdapter")
    MessageListenerAdapter listenerAdapter(MessageHandler subscriber) {
        return new MessageListenerAdapter(subscriber, "handleMessage");
    }
}
