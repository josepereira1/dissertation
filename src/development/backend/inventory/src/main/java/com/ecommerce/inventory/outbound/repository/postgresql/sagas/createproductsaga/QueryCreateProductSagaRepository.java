package com.ecommerce.inventory.outbound.repository.postgresql.sagas.createproductsaga;

import com.ecommerce.inventory.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.inventory.core.ports.out.repository.createproductsaga.IQueryCreateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryCreateProductSagaRepository implements IQueryCreateProductSagaRepository {

    @Autowired
    private CreateProductSagaProductDAO createProductSagaProductDAO;

    @Override
    public Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId) {
        return createProductSagaProductDAO.findCreateProductSagaBySagaId(sagaId);
    }
}
