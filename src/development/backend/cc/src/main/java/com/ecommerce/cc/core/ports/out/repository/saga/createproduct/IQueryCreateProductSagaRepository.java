package com.ecommerce.cc.core.ports.out.repository.saga.createproduct;

import com.ecommerce.cc.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import java.util.Optional;

public interface IQueryCreateProductSagaRepository {
    Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId);
}
