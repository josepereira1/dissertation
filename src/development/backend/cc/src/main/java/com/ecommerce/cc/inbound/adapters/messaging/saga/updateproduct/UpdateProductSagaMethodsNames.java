package com.ecommerce.cc.inbound.adapters.messaging.saga.updateproduct;

import com.ecommerce.cc.inbound.adapters.messaging.saga.ISagaMethods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductSagaMethodsNames implements ISagaMethods {

    @Value("${update.product.saga.0.cc.local.transaction.method.name}")
    private String localTransactionMethodName;

    @Value("${update.product.saga.0.cc.compensating.transaction.method.name}")
    private String compensatingTransaction;

    @Value("${update.product.saga.0.commit.method.name}")
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
