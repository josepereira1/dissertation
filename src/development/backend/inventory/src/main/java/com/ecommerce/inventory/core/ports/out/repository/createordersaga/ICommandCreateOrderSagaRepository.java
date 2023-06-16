package com.ecommerce.inventory.core.ports.out.repository.createordersaga;

import com.ecommerce.inventory.core.business.saga.sagas.createorder.resources.CreateOrderSaga;

public interface ICommandCreateOrderSagaRepository {
    CreateOrderSaga save(CreateOrderSaga order);
}
