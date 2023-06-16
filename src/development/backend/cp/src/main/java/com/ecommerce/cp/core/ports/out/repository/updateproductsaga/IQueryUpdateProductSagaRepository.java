package com.ecommerce.cp.core.ports.out.repository.updateproductsaga;

import com.ecommerce.cp.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import java.util.Optional;

public interface IQueryUpdateProductSagaRepository {
    Optional<UpdateProductSaga> findUpdateProductSagaBySagaId(String sagaId);
}
