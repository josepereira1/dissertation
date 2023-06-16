package com.ecommerce.sc.core.ports.out.repository.createordersaga;

import com.ecommerce.sc.core.business.sagas.createorder.resources.CreateOrderSaga;

public interface ICommandCreateOrderSagaRepository {
    CreateOrderSaga save(CreateOrderSaga createOrderSaga);
}
