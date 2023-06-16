package com.ecommerce.cp.core.ports.out.repository.createproductsaga;

import com.ecommerce.cp.core.business.saga.sagas.createproduct.resources.CreateProductSaga;

public interface ICommandCreateProductSagaRepository {
    CreateProductSaga save(CreateProductSaga createProductSaga);
    void deleteById(String id);
}
