package com.example.ecommerce.saga.outbound.mongo.saga;

import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface SagaDAO extends MongoRepository<Saga, String> {
    Collection<Saga> findAllByEntityId(String entityId);
}
