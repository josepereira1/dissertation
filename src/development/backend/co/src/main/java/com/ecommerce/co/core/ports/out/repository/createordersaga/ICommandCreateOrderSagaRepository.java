package com.ecommerce.co.core.ports.out.repository.createordersaga;

import com.ecommerce.co.core.business.saga.sagas.createorder.resources.CreateOrderSaga;

public interface ICommandCreateOrderSagaRepository {
    CreateOrderSaga save(CreateOrderSaga createOrderSaga);
    void deleteById(String id);
}
