package com.ecommerce.co.inbound.messaging;

import com.ecommerce.co.core.business.logs.ILogs;
import com.ecommerce.co.core.business.messaging.resources.IMessage;
import com.ecommerce.co.core.ports.in.saga.createorder.ICreateOrderSaga;
import com.ecommerce.co.inbound.messaging.messaging.consumer.MessageHandler;
import com.ecommerce.co.inbound.messaging.sagas.createorder.CreateOrderSagaMethodNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodSelection extends MessageHandler {

    @Autowired
    private CreateOrderSagaMethodNames createOrderSagaMethodNames;

    @Autowired
    private ICreateOrderSaga createOrderSaga;

    @Autowired
    private ILogs logs;

    @Override
    public void callMethod(String method, IMessage message) throws Exception {
        if(method.equals(createOrderSagaMethodNames.getLocalTransactionMethodName()))
            createOrderSaga.callLocalTransaction(message);
        else if(method.equals(createOrderSagaMethodNames.getCompensatingTransactionMethodName()))
            createOrderSaga.callCompensatingTransaction(message);
        else if(method.equals(createOrderSagaMethodNames.getCommitMethodName()))
            createOrderSaga.callCommit(message);
        else logs.logError("Method with text <<" + method + ">> not match any method - message: " + message);

    }
}
