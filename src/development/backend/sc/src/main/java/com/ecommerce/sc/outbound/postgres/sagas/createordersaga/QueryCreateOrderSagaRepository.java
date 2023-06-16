package com.ecommerce.sc.outbound.postgres.sagas.createordersaga;

import com.ecommerce.sc.core.business.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.sc.core.ports.out.repository.createordersaga.IQueryCreateOrderSagaRepository;
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
