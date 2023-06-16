package com.ecommerce.co.outbound.mongo.createordersaga;

import com.ecommerce.co.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreateOrderSagaDAO extends MongoRepository<CreateOrderSaga, String> {
    Optional<CreateOrderSaga> findCreateOrderSagaBySagaId(String sagaId);
}
