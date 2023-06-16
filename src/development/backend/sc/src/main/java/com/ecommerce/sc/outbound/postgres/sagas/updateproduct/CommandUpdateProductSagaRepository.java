package com.ecommerce.sc.outbound.postgres.sagas.updateproduct;

import com.ecommerce.sc.core.business.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.sc.core.ports.out.repository.updateproduct.ICommandUpdateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandUpdateProductSagaRepository implements ICommandUpdateProductSagaRepository {

    @Autowired
    private UpdateProductSagaDAO updateProductSagaDAO;

    @Override
    public UpdateProductSaga save(UpdateProductSaga updateProductSaga) {
        return updateProductSagaDAO.save(updateProductSaga);
    }
}
