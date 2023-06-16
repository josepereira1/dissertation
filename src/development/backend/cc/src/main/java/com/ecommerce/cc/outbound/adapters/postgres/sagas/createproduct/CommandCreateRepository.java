package com.ecommerce.cc.outbound.adapters.postgres.sagas.createproduct;

import com.ecommerce.cc.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.cc.core.ports.out.repository.saga.createproduct.ICommandCreateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandCreateRepository implements ICommandCreateProductSagaRepository {

    @Autowired
    private CreateProductSagaProductDAO createProductSagaProductDAO;

    @Override
    public CreateProductSaga save(CreateProductSaga createProductSaga) {
        return createProductSagaProductDAO.save(createProductSaga);
    }
}
