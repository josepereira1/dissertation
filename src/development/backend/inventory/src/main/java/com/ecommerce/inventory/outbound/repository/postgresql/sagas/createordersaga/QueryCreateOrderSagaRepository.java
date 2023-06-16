package com.ecommerce.inventory.outbound.repository.postgresql.sagas.createordersaga;

import com.ecommerce.inventory.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.inventory.core.ports.out.repository.createordersaga.IQueryCreateOrderSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryCreateOrderSagaRepository implements IQueryCreateOrderSagaRepository {

    @Autowired
    private CreateOrderSagaOrderDAO createOrderSagaOrderDAO;

    @Override
    public Optional<CreateOrderSaga> findCreateOrderSagaBySagaId(String sagaId) {
        return createOrderSagaOrderDAO.findCreateOrderSagaBySagaId(sagaId);
    }
}
