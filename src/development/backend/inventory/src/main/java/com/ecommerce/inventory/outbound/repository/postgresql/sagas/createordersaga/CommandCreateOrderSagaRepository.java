package com.ecommerce.inventory.outbound.repository.postgresql.sagas.createordersaga;

import com.ecommerce.inventory.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.inventory.core.ports.out.repository.createordersaga.ICommandCreateOrderSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandCreateOrderSagaRepository implements ICommandCreateOrderSagaRepository {

    @Autowired
    private CreateOrderSagaOrderDAO createOrderSagaOrderDAO;

    @Override
    public CreateOrderSaga save(CreateOrderSaga order) {
        return createOrderSagaOrderDAO.save(order);
    }
}
