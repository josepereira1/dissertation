package com.ecommerce.inventory.inbound.messaging.sagas.createproduct;

import com.ecommerce.inventory.inbound.messaging.sagas.ISagaMethods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateProductSagaMethodsNames implements ISagaMethods {

    @Value("${create.product.saga.0.inventory.local.transaction.method.name}")
    private String updateProductSagaLocalTransaction;

    @Value("${create.product.saga.0.inventory.compensating.transaction.method.name}")
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
