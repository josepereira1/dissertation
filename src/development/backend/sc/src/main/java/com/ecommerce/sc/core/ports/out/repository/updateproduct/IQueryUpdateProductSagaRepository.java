package com.ecommerce.sc.core.ports.out.repository.updateproduct;

import com.ecommerce.sc.core.business.sagas.updateproduct.resources.UpdateProductSaga;
import java.util.Optional;

public interface IQueryUpdateProductSagaRepository {
    Optional<UpdateProductSaga> findUpdateProductSagaBySagaId(String sagaId);
}
