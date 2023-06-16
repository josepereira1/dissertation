package com.ecommerce.consumer.core.ports.out.repository.consumer;

import com.ecommerce.consumer.core.business.resources.Consumer;

import java.util.Optional;

public interface IQueryConsumerRepository {
    Optional<Consumer> findById(String id);
    boolean existsById(String id);
}
