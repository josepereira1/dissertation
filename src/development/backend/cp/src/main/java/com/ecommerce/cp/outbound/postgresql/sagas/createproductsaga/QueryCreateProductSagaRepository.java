package com.ecommerce.cp.outbound.postgresql.sagas.createproductsaga;

import com.ecommerce.cp.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.cp.core.ports.out.repository.createproductsaga.IQueryCreateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryCreateProductSagaRepository implements IQueryCreateProductSagaRepository {

    @Autowired
    private CreateProductProductSagaDAO createProductProductSagaDAO;

    @Override
    public Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId) {
        return createProductProductSagaDAO.findCreateProductSagaBySagaId(sagaId);
    }
}
