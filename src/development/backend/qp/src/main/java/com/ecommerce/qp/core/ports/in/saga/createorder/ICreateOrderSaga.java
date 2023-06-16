package com.ecommerce.qp.core.ports.in.saga.createorder;

import com.ecommerce.qp.core.business.messaging.resources.IMessage;

public interface ICreateOrderSaga {
    void callLocalTransaction(IMessage message);
    void callCompensatingTransaction(IMessage message);
    void callCommit(IMessage message);
}
