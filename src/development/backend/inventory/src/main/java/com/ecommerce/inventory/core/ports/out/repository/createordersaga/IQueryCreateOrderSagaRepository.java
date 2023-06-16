package com.ecommerce.inventory.core.ports.out.repository.createordersaga;

import com.ecommerce.inventory.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import java.util.Optional;

public interface IQueryCreateOrderSagaRepository {
    Optional<CreateOrderSaga> findCreateOrderSagaBySagaId(String sagaId);
}
