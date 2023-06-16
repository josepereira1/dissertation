package com.ecommerce.co.core.business.saga.sagas.createorder;

import com.ecommerce.co.core.business.messaging.resources.IMessage;
import com.ecommerce.co.core.business.saga.sagas.createorder.features.CreateOrderSagaCommit;
import com.ecommerce.co.core.business.saga.sagas.createorder.features.CreateOrderSagaCompensatingTransaction;
import com.ecommerce.co.core.business.saga.sagas.createorder.features.CreateOrderSagaLocalTransaction;
import com.ecommerce.co.core.ports.in.saga.createorder.ICreateOrderSaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderSaga implements ICreateOrderSaga {

    @Autowired
    private CreateOrderSagaLocalTransaction createOrder;

    @Autowired
    private CreateOrderSagaCompensatingTransaction compensatingTransaction;

    @Autowired
    private CreateOrderSagaCommit commit;

    @Override
    public void callLocalTransaction(IMessage message) {
        createOrder.callLocalTransaction(message);
    }

    @Override
    public void callCompensatingTransaction(IMessage message) {
        compensatingTransaction.callCompensatingTransaction(message);
    }

    @Override
    public void callCommit(IMessage message) {
        commit.callCommit(message);
    }
}
