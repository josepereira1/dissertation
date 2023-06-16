package com.ecommerce.sc.core.ports.out.repository.updateproduct;

import com.ecommerce.sc.core.business.sagas.updateproduct.resources.UpdateProductSaga;

public interface ICommandUpdateProductSagaRepository {
    UpdateProductSaga save(UpdateProductSaga updateProductSaga);
}
