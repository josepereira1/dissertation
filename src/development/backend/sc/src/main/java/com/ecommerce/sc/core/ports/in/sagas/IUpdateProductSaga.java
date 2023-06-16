package com.ecommerce.sc.core.ports.in.sagas;

import com.ecommerce.sc.core.business.messaging.resources.IMessage;

public interface IUpdateProductSaga {
    void callLocalTransaction(IMessage message);
    void callCompensatingTransaction(IMessage message);
    void callCommit(IMessage message);
}
