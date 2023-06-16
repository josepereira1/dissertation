package com.ecommerce.sc.core.ports.out.repository.createproductsaga;

import com.ecommerce.sc.core.business.sagas.createproduct.resources.CreateProductSaga;

public interface ICommandCreateProductSagaRepository {
    CreateProductSaga save(CreateProductSaga createProductSaga);
}
