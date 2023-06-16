package com.ecommerce.qp.inbound.messaging.sagas;

public interface ISagaMethods {
    String getLocalTransactionMethodName();
    String getCompensatingTransactionMethodName();
    String getCommitMethodName();
}
