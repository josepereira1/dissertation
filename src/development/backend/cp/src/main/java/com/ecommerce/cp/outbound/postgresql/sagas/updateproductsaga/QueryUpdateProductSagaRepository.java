package com.ecommerce.cp.outbound.postgresql.sagas.updateproductsaga;

import com.ecommerce.cp.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.cp.core.ports.out.repository.updateproductsaga.IQueryUpdateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryUpdateProductSagaRepository implements IQueryUpdateProductSagaRepository {

    @Autowired
    private UpdateProductProductSagaDAO updateProductProductSagaDAO;

    public Optional<UpdateProductSaga> findUpdateProductSagaBySagaId(String sagaId) {
        return updateProductProductSagaDAO.findCreateProductSagaBySagaId(sagaId);
    }
}
