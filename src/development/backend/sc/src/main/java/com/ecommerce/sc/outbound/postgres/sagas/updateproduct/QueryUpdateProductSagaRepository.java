package com.ecommerce.sc.outbound.postgres.sagas.updateproduct;

import com.ecommerce.sc.core.business.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.sc.core.ports.out.repository.updateproduct.IQueryUpdateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryUpdateProductSagaRepository implements IQueryUpdateProductSagaRepository {

    @Autowired
    private UpdateProductSagaDAO updateProductSagaDAO;

    @Override
    public Optional<UpdateProductSaga> findUpdateProductSagaBySagaId(String sagaId) {
        return updateProductSagaDAO.findUpdateProductSagaBySagaId(sagaId);
    }
}
