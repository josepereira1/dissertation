package com.ecommerce.cp.inbound.messaging.sagas.updateproduct;

import com.ecommerce.cp.inbound.messaging.sagas.ISagaMethods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductSagaMethodsNames implements ISagaMethods {

    @Value("${update.product.saga.0.cp.local.transaction.method.name}")
    private String updateProductSagaLocalTransaction;

    @Value("${update.product.saga.0.cp.compensating.transaction.method.name}")
    private String updateProductSagaCompensatingTransaction;

    @Value("${update.product.saga.0.commit.method.name}")
    private String updateProductSagaCommit;

    @Override
    public String getLocalTransactionMethodName(){
        return updateProductSagaLocalTransaction;
    }

    @Override
    public String getCompensatingTransactionMethodName(){
        return updateProductSagaCompensatingTransaction;
    }

    @Override
    public String getCommitMethodName(){
        return updateProductSagaCommit;
    }
}
