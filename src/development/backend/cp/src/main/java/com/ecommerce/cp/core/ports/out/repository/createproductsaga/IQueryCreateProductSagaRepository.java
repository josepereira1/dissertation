package com.ecommerce.cp.core.ports.out.repository.createproductsaga;

import com.ecommerce.cp.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import java.util.Optional;

public interface IQueryCreateProductSagaRepository {
    Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId);
}
