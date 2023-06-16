package com.ecommerce.inventory.inbound.messaging;

import com.ecommerce.inventory.core.business.logs.ILogs;
import com.ecommerce.inventory.core.business.messaging.resources.IMessage;
import com.ecommerce.inventory.core.ports.in.saga.ICreateOrderSaga;
import com.ecommerce.inventory.core.ports.in.saga.IUpdateProduct;
import com.ecommerce.inventory.inbound.messaging.messaging.consumer.MessageHandler;
import com.ecommerce.inventory.core.ports.in.saga.ICreateProductSaga;
import com.ecommerce.inventory.inbound.messaging.sagas.createproduct.CreateProductSagaMethodsNames;
import com.ecommerce.inventory.inbound.messaging.sagas.updateproduct.UpdateProductSagaMethodsNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodSelection extends MessageHandler {

    @Autowired
    private CreateProductSagaMethodsNames createProductSagaMethodsNames;

    @Autowired
    private ICreateProductSaga createProductSaga;

    @Autowired
    private UpdateProductSagaMethodsNames updateProductSagaMethodsNames;

    @Autowired
    private IUpdateProduct updateProduct;

    @Autowired
    private ICreateOrderSaga createOrderSaga;

    @Autowired
    private ILogs logs;

    @Override
    public void callMethod(String method, IMessage message) throws Exception {
        if(method.equals(createProductSagaMethodsNames.getLocalTransactionMethodName()))
            createProductSaga.callLocalTransaction(message);
        else if(method.equals(createProductSagaMethodsNames.getCompensatingTransactionMethodName()))
            createProductSaga.callCompensatingTransaction(message);
        else if(method.equals(createProductSagaMethodsNames.getCommitMethodName()))
            createProductSaga.callCommit(message);
        else if(method.equals(ICreateOrderSaga.CREATE_ORDER_SAGA_LOCAL_TRANSACTION))
            createOrderSaga.callLocalTransaction(message);
        else if(method.equals(ICreateOrderSaga.CREATE_ORDER_SAGA_COMPENSABLE_TRANSACTION))
            createOrderSaga.callCompensatingTransaction(message);
        else if(method.equals(ICreateOrderSaga.CREATE_ORDER_SAGA_COMMIT))
            createOrderSaga.callCommit(message);
        else if(method.equals(updateProductSagaMethodsNames.getLocalTransactionMethodName()))
            updateProduct.callLocalTransaction(message);
        else if(method.equals(updateProductSagaMethodsNames.getCompensatingTransactionMethodName()))
            updateProduct.callCompensatingTransaction(message);
        else if(method.equals(updateProductSagaMethodsNames.getCommitMethodName()))
            updateProduct.callCommit(message);
        else logs.logError("Method with text <<" + method + ">> not match any method - message: " + message);
    }
}
