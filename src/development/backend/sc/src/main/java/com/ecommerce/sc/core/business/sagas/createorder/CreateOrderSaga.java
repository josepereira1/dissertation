package com.ecommerce.sc.core.business.sagas.createorder;

import com.ecommerce.sc.core.business.messaging.resources.IMessage;
import com.ecommerce.sc.core.business.sagas.createorder.features.CreateOrderSagaCommit;
import com.ecommerce.sc.core.business.sagas.createorder.features.CreateOrderSagaCompensatingTransaction;
import com.ecommerce.sc.core.business.sagas.createorder.features.CreateOrderSagaLocalTransaction;
import com.ecommerce.sc.core.ports.in.sagas.ICreateOrderSaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderSaga implements ICreateOrderSaga {

    @Autowired
    private CreateOrderSagaLocalTransaction createOrderSagaLocalTransaction;

    @Autowired
    private CreateOrderSagaCompensatingTransaction createOrderSagaCompensatingTransaction;

    @Autowired
    private CreateOrderSagaCommit createOrderSagaCommit;

    @Override
    public void callLocalTransaction(IMessage message) {
        createOrderSagaLocalTransaction.callLocalTransaction(message);
    }

    @Override
    public void callCompensatingTransaction(IMessage message) {
        createOrderSagaCompensatingTransaction.callCompensatingTransaction(message);
    }

    @Override
    public void callCommit(IMessage message) {
        createOrderSagaCommit.callCommit(message);
    }
}
