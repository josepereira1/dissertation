package com.ecommerce.sc.outbound.postgres.sagas.createproductsaga;

import com.ecommerce.sc.core.business.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.sc.core.ports.out.repository.createproductsaga.ICommandCreateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandCreateProductSagaRepository implements ICommandCreateProductSagaRepository {

    @Autowired
    private CreateProductSagaDAO createProductSagaDAO;

    @Override
    public CreateProductSaga save(CreateProductSaga createProductSaga) {
        return createProductSagaDAO.save(createProductSaga);
    }
}
