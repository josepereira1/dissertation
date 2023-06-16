package com.ecommerce.sc.outbound.postgres.sagas.createordersaga;

import com.ecommerce.sc.core.business.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.sc.core.ports.out.repository.createordersaga.ICommandCreateOrderSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandCreateOrderSagaRepository implements ICommandCreateOrderSagaRepository {

    @Autowired
    private CreateOrderSagaDAO createOrderSagaDAO;

    @Override
    public CreateOrderSaga save(CreateOrderSaga createOrderSaga) {
        return createOrderSagaDAO.save(createOrderSaga);
    }
}
