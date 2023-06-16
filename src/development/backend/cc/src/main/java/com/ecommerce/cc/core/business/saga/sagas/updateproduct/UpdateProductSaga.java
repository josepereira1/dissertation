package com.ecommerce.cc.core.business.saga.sagas.updateproduct;

import com.ecommerce.cc.core.business.messaging.resources.IMessage;
import com.ecommerce.cc.core.business.saga.sagas.updateproduct.features.UpdateProductSagaCommit;
import com.ecommerce.cc.core.business.saga.sagas.updateproduct.features.UpdateProductSagaCompensatingTransaction;
import com.ecommerce.cc.core.business.saga.sagas.updateproduct.features.UpdateProductSagaLocalTransaction;
import com.ecommerce.cc.core.ports.in.saga.IUpdateProductSaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductSaga implements IUpdateProductSaga {

    @Autowired
    private UpdateProductSagaLocalTransaction updateProductSagaLocalTransaction;

    @Autowired
    private UpdateProductSagaCompensatingTransaction updateProductSagaCompensatingTransaction;

    @Autowired
    private UpdateProductSagaCommit updateProductSagaCommit;

    @Override
    public void callLocalTransaction(IMessage message) {
        updateProductSagaLocalTransaction.callLocalTransaction(message);
    }

    @Override
    public void callCompensatingTransaction(IMessage message) {
        updateProductSagaCompensatingTransaction.callCompensatingTransaction(message);
    }

    @Override
    public void callCommit(IMessage message) {
        updateProductSagaCommit.callCommit(message);
    }
}
