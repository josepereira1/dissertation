package com.ecommerce.co.outbound.mongo.createordersaga;

import com.ecommerce.co.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.co.core.ports.out.repository.createordersaga.IQueryCreateOrderSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryCreateOrderSagaRepository implements IQueryCreateOrderSagaRepository {

    @Autowired
    private CreateOrderSagaDAO createOrderSagaDAO;

    @Override
    public Optional<CreateOrderSaga> findCreateOrderSagaBySagaId(String sagaId) {
        return createOrderSagaDAO.findCreateOrderSagaBySagaId(sagaId);
    }
}
