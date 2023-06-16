package com.ecommerce.inventory.core.business.saga.sagas.createorder;

import com.ecommerce.inventory.core.business.messaging.resources.IMessage;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.features.CreateOrderSagaCommit;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.features.CreateOrderSagaCompensatingTransaction;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.features.CreateOrderSagaLocalTransaction;
import com.ecommerce.inventory.core.ports.in.saga.ICreateOrderSaga;
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
