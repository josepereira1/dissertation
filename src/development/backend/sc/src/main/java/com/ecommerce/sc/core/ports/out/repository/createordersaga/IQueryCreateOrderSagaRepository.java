package com.ecommerce.sc.core.ports.out.repository.createordersaga;

import com.ecommerce.sc.core.business.sagas.createorder.resources.CreateOrderSaga;
import java.util.Optional;

public interface IQueryCreateOrderSagaRepository {
    Optional<CreateOrderSaga> findCreateOrderSagaBySagaId(String sagaId);
}
