package com.ecommerce.sc.outbound.postgres.sagas.createproductsaga;

import com.ecommerce.sc.core.business.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.sc.core.ports.out.repository.createproductsaga.IQueryCreateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryCreateProductSagaRepository implements IQueryCreateProductSagaRepository {

    @Autowired
    private CreateProductSagaDAO createProductSagaDAO;

    @Override
    public Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId) {
        return createProductSagaDAO.findCreateProductSagaBySagaId(sagaId);
    }
}
