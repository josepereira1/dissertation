package com.ecommerce.inventory.inbound.messaging.sagas;

public interface ISagaMethods {
    String getLocalTransactionMethodName();
    String getCompensatingTransactionMethodName();
    String getCommitMethodName();
}
