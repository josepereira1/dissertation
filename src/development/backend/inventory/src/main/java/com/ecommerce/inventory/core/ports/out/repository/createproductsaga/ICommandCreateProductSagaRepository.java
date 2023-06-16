package com.ecommerce.inventory.core.ports.out.repository.createproductsaga;

import com.ecommerce.inventory.core.business.saga.sagas.createproduct.resources.CreateProductSaga;

public interface ICommandCreateProductSagaRepository {
    CreateProductSaga save(CreateProductSaga product);
}
