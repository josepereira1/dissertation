package com.ecommerce.cc.inbound.adapters.messaging.saga;

public interface ISagaMethods {
    String getLocalTransactionMethodName();
    String getCompensatingTransactionMethodName();
    String getCommitMethodName();
}
