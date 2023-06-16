package com.ecommerce.cc.core.ports.in.saga;

import com.ecommerce.cc.core.business.messaging.resources.IMessage;

public interface IUpdateProductSaga {
    void callLocalTransaction(IMessage message);
    void callCompensatingTransaction(IMessage message);
    void callCommit(IMessage message);
}
