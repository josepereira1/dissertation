package com.ecommerce.consumer.outbound.repository.mongo.consumer;

import com.ecommerce.consumer.core.business.resources.Consumer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerDAO extends MongoRepository<Consumer, String> {
}
