package com.ecommerce.inventory.core.ports.out.repository.updateproductsaga;

import com.ecommerce.inventory.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;

public interface ICommandUpdateProductSagaRepository {
    UpdateProductSaga save(UpdateProductSaga product);
    void deleteById(String id);
}
