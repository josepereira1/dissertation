package com.ecommerce.inventory.core.ports.in.saga;

import com.ecommerce.inventory.core.business.messaging.resources.IMessage;

public interface IUpdateProduct {
    void callLocalTransaction(IMessage message);
    void callCompensatingTransaction(IMessage message);
    void callCommit(IMessage message);
}
