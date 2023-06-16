package com.ecommerce.cp.inbound.messaging;

import com.ecommerce.cp.core.business.logs.ILogs;
import com.ecommerce.cp.core.business.messaging.resources.IMessage;
import com.ecommerce.cp.core.ports.in.saga.ICreateProductSaga;
import com.ecommerce.cp.core.ports.in.saga.IUpdateProductSaga;
import com.ecommerce.cp.inbound.messaging.cqrs.product.CQRSProduct;
import com.ecommerce.cp.inbound.messaging.messaging.consumer.MessageHandler;
import com.ecommerce.cp.inbound.messaging.sagas.createproduct.CreateProductSagaMethodNames;
import com.ecommerce.cp.inbound.messaging.sagas.updateproduct.UpdateProductSagaMethodsNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodSelection extends MessageHandler {

    @Autowired
    private CreateProductSagaMethodNames createProductSagaMethodNames;

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
        if(method.equals(createProductSagaMethodNames.getLocalTransactionMethodName())){
            createProductSaga.callLocalTransaction(message);
        } else if(method.equals(createProductSagaMethodNames.getCompensatingTransactionMethodName())){
            createProductSaga.callCompensatingTransaction(message);
        } else if(method.equals(createProductSagaMethodNames.getCommitMethodName())){
            createProductSaga.callCommit(message);
        } else if(method.equals(CQRSProduct.CQRS_UPDATE_STOCK_STATUS)){
            cqrsProduct.cqrsUpdateStockStatus(message);
        } else if(method.equals(updateProductSagaMethodsNames.getLocalTransactionMethodName())){
            updateProductSaga.callLocalTransaction(message);
        } else if(method.equals(updateProductSagaMethodsNames.getCompensatingTransactionMethodName())){
            updateProductSaga.callCompensatingTransaction(message);
        } else if(method.equals(updateProductSagaMethodsNames.getCommitMethodName())){
            updateProductSaga.callCommit(message);
        } else logs.logError("Method with text <<" + method + ">> not match any method - message: " + message);
    }
}
