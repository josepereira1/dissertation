package com.ecommerce.inventory.core.business.saga.sagas.createproduct;

import com.ecommerce.inventory.core.business.messaging.resources.IMessage;
import com.ecommerce.inventory.core.business.saga.sagas.createproduct.features.CreateProductSagaCommit;
import com.ecommerce.inventory.core.business.saga.sagas.createproduct.features.CreateProductSagaLocalTransaction;
import com.ecommerce.inventory.core.business.saga.sagas.createproduct.features.CreateProductSagaCompensatingTransaction;
import com.ecommerce.inventory.core.ports.in.saga.ICreateProductSaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductSaga implements ICreateProductSaga {

    @Autowired
    private CreateProductSagaLocalTransaction createProductSagaLocalTransaction;

    @Autowired
    private CreateProductSagaCompensatingTransaction createProductSagaCompensatingTransaction;

    @Autowired
    private CreateProductSagaCommit createProductSagaCommit;

    @Override
    public void callLocalTransaction(IMessage message) {
        createProductSagaLocalTransaction.callLocalTransaction(message);
    }

    @Override
    public void callCompensatingTransaction(IMessage message) {
        createProductSagaCompensatingTransaction.callCompensatingTransaction(message);
    }

    @Override
    public void callCommit(IMessage message) {
        createProductSagaCommit.callCommit(message);
    }
}
