package com.ecommerce.co.core.ports.out.repository.createordersaga;

import com.ecommerce.co.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import java.util.Optional;

public interface IQueryCreateOrderSagaRepository {
    Optional<CreateOrderSaga> findCreateOrderSagaBySagaId(String sagaId);
}
