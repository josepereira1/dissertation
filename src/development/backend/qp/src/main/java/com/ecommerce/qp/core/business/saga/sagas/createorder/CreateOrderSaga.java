package com.ecommerce.qp.core.business.saga.sagas.createorder;

import com.ecommerce.qp.core.business.messaging.resources.IMessage;
import com.ecommerce.qp.core.business.saga.sagas.createorder.features.CreateOrderSagaCompensatingTransaction;
import com.ecommerce.qp.core.business.saga.sagas.createorder.features.CreateOrderSagaLocalTransaction;
import com.ecommerce.qp.core.ports.in.saga.createorder.ICreateOrderSaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderSaga implements ICreateOrderSaga {

    @Autowired
    private CreateOrderSagaLocalTransaction createOrderSagaLocalTransaction;

    @Autowired
    private CreateOrderSagaCompensatingTransaction createOrderSagaCompensatingTransaction;

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

    }
}
