package com.ecommerce.sc.core.ports.out.repository.createproductsaga;

import com.ecommerce.sc.core.business.sagas.createproduct.resources.CreateProductSaga;
import java.util.Optional;

public interface IQueryCreateProductSagaRepository {
    Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId);
}
