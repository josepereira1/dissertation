package com.ecommerce.cc.inbound.adapters.messaging;

import com.ecommerce.cc.core.business.logs.ILogs;
import com.ecommerce.cc.core.business.messaging.resources.IMessage;
import com.ecommerce.cc.core.ports.in.saga.IUpdateProductSaga;
import com.ecommerce.cc.inbound.adapters.messaging.cqrs.product.CQRSProduct;
import com.ecommerce.cc.inbound.adapters.messaging.messaging.consumer.MessageHandler;
import com.ecommerce.cc.core.ports.in.saga.ICreateProductSaga;
import com.ecommerce.cc.inbound.adapters.messaging.saga.createproduct.CreateProductSagaMethodsNames;
import com.ecommerce.cc.inbound.adapters.messaging.saga.updateproduct.UpdateProductSagaMethodsNames;
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
    private IUpdateProductSaga updateProductSaga;

    @Autowired
    private CQRSProduct cqrsProduct;

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
        else if(method.equals(updateProductSagaMethodsNames.getLocalTransactionMethodName()))
            updateProductSaga.callLocalTransaction(message);
        else if(method.equals(updateProductSagaMethodsNames.getCompensatingTransactionMethodName()))
            updateProductSaga.callCompensatingTransaction(message);
        else if(method.equals(updateProductSagaMethodsNames.getCommitMethodName()))
            updateProductSaga.callCommit(message);
        else if (method.equals(CQRSProduct.CQRS_UPDATE_STOCK_STATUS_METHOD_NAME))
            cqrsProduct.cqrsUpdateStockStatus(message);
        else logs.logError("Method with text <<" + method + ">> not match any method - message: " + message);

    }
}
