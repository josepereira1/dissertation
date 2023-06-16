package com.ecommerce.cp.core.ports.out.repository.updateproductsaga;

import com.ecommerce.cp.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;

public interface ICommandUpdateProductSagaRepository {
    UpdateProductSaga save(UpdateProductSaga product);
}
