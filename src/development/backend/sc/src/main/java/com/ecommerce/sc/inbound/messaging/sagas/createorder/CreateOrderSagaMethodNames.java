package com.ecommerce.sc.inbound.messaging.sagas.createorder;

import com.ecommerce.sc.inbound.messaging.sagas.ISagaMethods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderSagaMethodNames implements ISagaMethods {

    @Value("${create.order.saga.0.sc.local.transaction.method.name}")
    private String localTransaction;

    @Value("${create.order.saga.0.sc.compensating.transaction.method.name}")
    private String compensatingTransaction;

    @Value("${create.order.saga.0.commit.method.name}")
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
