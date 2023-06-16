package com.ecommerce.cc.outbound.adapters.postgres.sagas.updateproduct;

import com.ecommerce.cc.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.cc.core.ports.out.repository.saga.updateproduct.IQueryUpdateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class QueryUpdateProductSagaRepository implements IQueryUpdateProductSagaRepository {

    @Autowired
    private UpdateProductSagaProductDAO updateProductSagaProductDAO;

    @Override
    public Optional<UpdateProductSaga> findOneForUpdate(String sagaId) {
        return updateProductSagaProductDAO.findOneForUpdate(sagaId);
    }
}
