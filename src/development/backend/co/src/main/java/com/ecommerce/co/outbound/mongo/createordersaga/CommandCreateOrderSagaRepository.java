package com.ecommerce.co.outbound.mongo.createordersaga;

import com.ecommerce.co.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.co.core.ports.out.repository.createordersaga.ICommandCreateOrderSagaRepository;
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

    @Override
    public void deleteById(String id) {
        createOrderSagaDAO.deleteById(id);
    }
}
