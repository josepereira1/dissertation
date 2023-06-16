package com.ecommerce.inventory.outbound.repository.postgresql.sagas.updateproductsaga;

import com.ecommerce.inventory.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.inventory.core.ports.out.repository.updateproductsaga.IQueryUpdateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryUpdateProductSagaRepository implements IQueryUpdateProductSagaRepository {

    @Autowired
    private UpdateProductSagaProductDAO updateProductSagaProductDAO;

    @Override
    public Optional<UpdateProductSaga> findUpdateProductSagaBySagaId(String sagaId) {
        return updateProductSagaProductDAO.findUpdateProductSagaBySagaId(sagaId);
    }
}
