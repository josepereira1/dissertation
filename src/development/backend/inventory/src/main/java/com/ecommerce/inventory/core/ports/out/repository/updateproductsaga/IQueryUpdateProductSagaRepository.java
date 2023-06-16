package com.ecommerce.inventory.core.ports.out.repository.updateproductsaga;

import com.ecommerce.inventory.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import java.util.Optional;

public interface IQueryUpdateProductSagaRepository {
    Optional<UpdateProductSaga> findUpdateProductSagaBySagaId(String sagaId);
}
