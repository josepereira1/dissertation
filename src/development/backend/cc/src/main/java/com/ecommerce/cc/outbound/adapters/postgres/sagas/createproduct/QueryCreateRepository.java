package com.ecommerce.cc.outbound.adapters.postgres.sagas.createproduct;

import com.ecommerce.cc.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.cc.core.ports.out.repository.saga.createproduct.IQueryCreateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class QueryCreateRepository implements IQueryCreateProductSagaRepository {

    @Autowired
    private CreateProductSagaProductDAO createProductSagaProductDAO;

    @Override
    public Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId) {
        return createProductSagaProductDAO.findCreateProductSagaBySagaId(sagaId);
    }
}
