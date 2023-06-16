package com.ecommerce.cc.core.ports.out.repository.saga.updateproduct;

import com.ecommerce.cc.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;

public interface ICommandUpdateProductSagaRepository {
    UpdateProductSaga save(UpdateProductSaga updateProductSaga);
    void delete(Long id);
}
