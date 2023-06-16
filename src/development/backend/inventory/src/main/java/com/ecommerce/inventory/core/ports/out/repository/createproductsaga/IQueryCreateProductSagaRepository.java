package com.ecommerce.inventory.core.ports.out.repository.createproductsaga;

import com.ecommerce.inventory.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import java.util.Optional;

public interface IQueryCreateProductSagaRepository {
    Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId);
}
