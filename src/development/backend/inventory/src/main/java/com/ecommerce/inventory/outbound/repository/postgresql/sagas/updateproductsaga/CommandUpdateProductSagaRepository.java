package com.ecommerce.inventory.outbound.repository.postgresql.sagas.updateproductsaga;

import com.ecommerce.inventory.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.inventory.core.ports.out.repository.updateproductsaga.ICommandUpdateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandUpdateProductSagaRepository implements ICommandUpdateProductSagaRepository {

    @Autowired
    private UpdateProductSagaProductDAO updateProductSagaProductDAO;

    @Override
    public UpdateProductSaga save(UpdateProductSaga product) {
        return updateProductSagaProductDAO.save(product);
    }

    @Override
    public void deleteById(String id) {
        updateProductSagaProductDAO.deleteById(id);
    }
}
