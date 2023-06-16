package com.ecommerce.cc.outbound.adapters.postgres.sagas.updateproduct;

import com.ecommerce.cc.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.cc.core.ports.out.repository.saga.updateproduct.ICommandUpdateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandUpdateRepository implements ICommandUpdateProductSagaRepository {

    @Autowired
    private UpdateProductSagaProductDAO updateProductSagaProductDAO;

    @Override
    public UpdateProductSaga save(UpdateProductSaga updateProductSaga) {
        return updateProductSagaProductDAO.save(updateProductSaga);
    }

    @Override
    public void delete(Long id) {
        updateProductSagaProductDAO.deleteById(id);
    }
}
