package com.ecommerce.cp.outbound.postgresql.sagas.createproductsaga;

import com.ecommerce.cp.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.cp.core.ports.out.repository.createproductsaga.ICommandCreateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandCreateProductSagaRepository implements ICommandCreateProductSagaRepository {

    @Autowired
    private CreateProductProductSagaDAO createProductProductSagaDAO;

    @Override
    public CreateProductSaga save(CreateProductSaga createProductSaga) {
        return createProductProductSagaDAO.save(createProductSaga);
    }

    @Override
    public void deleteById(String id) {
        createProductProductSagaDAO.deleteById(id);
    }
}
