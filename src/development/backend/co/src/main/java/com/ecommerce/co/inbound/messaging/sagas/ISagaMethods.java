package com.ecommerce.co.inbound.messaging.sagas;

public interface ISagaMethods {
    String getLocalTransactionMethodName();
    String getCompensatingTransactionMethodName();
    String getCommitMethodName();
}
