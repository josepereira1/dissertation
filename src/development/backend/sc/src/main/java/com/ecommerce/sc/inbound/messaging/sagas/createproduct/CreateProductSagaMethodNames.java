package com.ecommerce.sc.inbound.messaging.sagas.createproduct;

import com.ecommerce.sc.inbound.messaging.sagas.ISagaMethods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateProductSagaMethodNames implements ISagaMethods {

    @Value("${create.product.saga.0.sc.local.transaction.method.name}")
    private String localTransaction;

    @Value("${create.product.saga.0.sc.compensating.transaction.method.name}")
    private String compensatingTransaction;

    @Value("${create.product.saga.0.commit.method.name}")
    private String commit;

    @Override
    public String getLocalTransactionMethodName(){
        return localTransaction;
    }

    @Override
    public String getCompensatingTransactionMethodName(){
        return compensatingTransaction;
    }

    @Override
    public String getCommitMethodName(){
        return commit;
    }
}
