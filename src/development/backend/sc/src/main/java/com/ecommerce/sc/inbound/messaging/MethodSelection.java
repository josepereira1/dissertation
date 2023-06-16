package com.ecommerce.sc.inbound.messaging;

import com.ecommerce.sc.core.business.logs.ILogs;
import com.ecommerce.sc.core.business.messaging.resources.IMessage;
import com.ecommerce.sc.core.ports.in.sagas.ICreateOrderSaga;
import com.ecommerce.sc.core.ports.in.sagas.ICreateProductSaga;
import com.ecommerce.sc.core.ports.in.sagas.IUpdateProductSaga;
import com.ecommerce.sc.inbound.messaging.messaging.consumer.MessageHandler;
import com.ecommerce.sc.inbound.messaging.sagas.createorder.CreateOrderSagaMethodNames;
import com.ecommerce.sc.inbound.messaging.sagas.createproduct.CreateProductSagaMethodNames;
import com.ecommerce.sc.inbound.messaging.sagas.updateproduct.UpdateProductSagaMethodNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodSelection extends MessageHandler {

    @Autowired
    private CreateOrderSagaMethodNames createOrderSagaMethodNames;

    @Autowired
    private ICreateOrderSaga createOrderSaga;

    @Autowired
    private CreateProductSagaMethodNames createProductSagaMethodNames;

    @Autowired
    private ICreateProductSaga createProductSaga;

    @Autowired
    private UpdateProductSagaMethodNames updateProductSagaMethodNames;

    @Autowired
    private IUpdateProductSaga updateProductSaga;

    @Autowired
    private ILogs logs;

    @Override
    public void callMethod(String method, IMessage message) throws Exception {
        //  CREATE ORDER SAGA
        if(method.equals(createOrderSagaMethodNames.getLocalTransactionMethodName()))
            createOrderSaga.callLocalTransaction(message);
        else if(method.equals(createOrderSagaMethodNames.getCompensatingTransactionMethodName()))
            createOrderSaga.callCompensatingTransaction(message);
        else if(method.equals(createOrderSagaMethodNames.getCommitMethodName()))
            createOrderSaga.callCommit(message);
        //  CREATE PRODUCT
        else if(method.equals(createProductSagaMethodNames.getLocalTransactionMethodName()))
            createProductSaga.callLocalTransaction(message);
        else if(method.equals(createProductSagaMethodNames.getCompensatingTransactionMethodName()))
            createProductSaga.callCompensatingTransaction(message);
        else if(method.equals(createProductSagaMethodNames.getCommitMethodName()))
            createProductSaga.callCommit(message);
        //  UPDATE PRODUCT
        else if(method.equals(updateProductSagaMethodNames.getLocalTransactionMethodName()))
            updateProductSaga.callLocalTransaction(message);
        else if(method.equals(updateProductSagaMethodNames.getCompensatingTransactionMethodName()))
            updateProductSaga.callCompensatingTransaction(message);
        else if(method.equals(updateProductSagaMethodNames.getCommitMethodName()))
            updateProductSaga.callCommit(message);
        else logs.logError("Method with text <<" + method + ">> not match any method - message: " + message);

    }
}
