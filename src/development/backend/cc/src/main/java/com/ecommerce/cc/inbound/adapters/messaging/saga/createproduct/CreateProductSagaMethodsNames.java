package com.ecommerce.cc.inbound.adapters.messaging.saga.createproduct;

import com.ecommerce.cc.inbound.adapters.messaging.saga.ISagaMethods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateProductSagaMethodsNames implements ISagaMethods {

    @Value("${create.product.saga.0.cc.local.transaction.method.name}")
    private String localTransactionMethodName;

    @Value("${create.product.saga.0.cc.compensating.transaction.method.name}")
    private String compensatingTransaction;

    @Value("${create.product.saga.0.commit.method.name}")
    private String commit;


    @Override
    public String getLocalTransactionMethodName() {
        return localTransactionMethodName;
    }

    @Override
    public String getCompensatingTransactionMethodName() {
        return compensatingTransaction;
    }

    @Override
    public String getCommitMethodName() {
        return commit;
    }
}
