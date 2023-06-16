package com.ecommerce.qp.inbound.messaging;

import com.ecommerce.qp.core.business.logs.ILogs;
import com.ecommerce.qp.core.business.messaging.resources.IMessage;
import com.ecommerce.qp.inbound.messaging.cqrs.product.CQRSProduct;
import com.ecommerce.qp.inbound.messaging.messaging.consumer.MessageHandler;
import com.ecommerce.qp.core.ports.in.saga.createorder.ICreateOrderSaga;
import com.ecommerce.qp.inbound.messaging.sagas.createorder.CreateOrderSagaMethodNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodSelection extends MessageHandler {

    @Autowired
    private CreateOrderSagaMethodNames createOrderSagaMethodNames;

    @Autowired
    private ICreateOrderSaga createOrderSaga;

    @Autowired
    private CQRSProduct cqrsProduct;

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
        else if(method.equals(CQRSProduct.CQRS_0_CREATE_PRODUCT))
            cqrsProduct.cqrsCreateProduct(message);
        else if (method.equals(CQRSProduct.CQRS_0_UPDATE_PRODUCT))
            cqrsProduct.cqrsUpdateProduct(message);
        else if (method.equals(CQRSProduct.CQRS_DELETE_PRODUCT))
            cqrsProduct.cqrsDeleteProduct(message);
        else logs.logError("Method with text <<" + method + ">> not match any method - message: " + message);
    }
}
