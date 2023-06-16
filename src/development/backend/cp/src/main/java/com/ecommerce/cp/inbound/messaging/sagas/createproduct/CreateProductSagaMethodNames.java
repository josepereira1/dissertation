package com.ecommerce.cp.inbound.messaging.sagas.createproduct;

import com.ecommerce.cp.inbound.messaging.sagas.ISagaMethods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateProductSagaMethodNames implements ISagaMethods {

    @Value("${create.product.saga.0.cp.local.transaction.method.name}")
    private String updateProductSagaLocalTransaction;

    @Value("${create.product.saga.0.cp.compensating.transaction.method.name}")
    private String updateProductSagaCompensatingTransaction;

    @Value("${create.product.saga.0.commit.method.name}")
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
