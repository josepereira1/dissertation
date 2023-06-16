package com.ecommerce.cp.outbound.postgresql.sagas.updateproductsaga;

import com.ecommerce.cp.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.cp.core.ports.out.repository.updateproductsaga.ICommandUpdateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandUpdateProductSagaRepository implements ICommandUpdateProductSagaRepository {

    @Autowired
    private UpdateProductProductSagaDAO updateProductProductSagaDAO;

    public UpdateProductSaga save(UpdateProductSaga createProductSagaProductEntityUncommitted) {
        return updateProductProductSagaDAO.save(createProductSagaProductEntityUncommitted);
    }
}
