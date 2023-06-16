package com.ecommerce.consumer.core.ports.out.repository.consumer;

import com.ecommerce.consumer.core.business.resources.Consumer;

public interface ICommandConsumerRepository {
    Consumer save(Consumer consumer);
}
