package com.ecommerce.cc.core.ports.out.repository.saga.createproduct;

import com.ecommerce.cc.core.business.saga.sagas.createproduct.resources.CreateProductSaga;

public interface ICommandCreateProductSagaRepository {
    CreateProductSaga save(CreateProductSaga createProductSaga);
}
