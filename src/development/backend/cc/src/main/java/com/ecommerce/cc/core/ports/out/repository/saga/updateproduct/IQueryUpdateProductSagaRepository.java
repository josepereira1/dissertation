package com.ecommerce.cc.core.ports.out.repository.saga.updateproduct;

import com.ecommerce.cc.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import java.util.Optional;

public interface IQueryUpdateProductSagaRepository {
    Optional<UpdateProductSaga> findOneForUpdate(String sagaId);
}
