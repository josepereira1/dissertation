package com.ecommerce.inventory.outbound.repository.postgresql.sagas.createproductsaga;

import com.ecommerce.inventory.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.inventory.core.ports.out.repository.createproductsaga.ICommandCreateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandCreateProductSagaRepository implements ICommandCreateProductSagaRepository {

    @Autowired
    private CreateProductSagaProductDAO createProductSagaProductDAO;

    @Override
    public CreateProductSaga save(CreateProductSaga createProductSaga) {
        return createProductSagaProductDAO.save(createProductSaga);
    }
}
