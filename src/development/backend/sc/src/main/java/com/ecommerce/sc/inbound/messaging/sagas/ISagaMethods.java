package com.ecommerce.sc.inbound.messaging.sagas;

public interface ISagaMethods {
    String getLocalTransactionMethodName();
    String getCompensatingTransactionMethodName();
    String getCommitMethodName();
}
