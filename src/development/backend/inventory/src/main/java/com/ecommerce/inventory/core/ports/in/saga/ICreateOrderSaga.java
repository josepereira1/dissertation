package com.ecommerce.inventory.core.ports.in.saga;

import com.ecommerce.inventory.core.business.messaging.resources.IMessage;

public interface ICreateOrderSaga {
    String CREATE_ORDER_SAGA_LOCAL_TRANSACTION = "create.order.saga.0.inventory.local.transaction";
    String CREATE_ORDER_SAGA_COMPENSABLE_TRANSACTION = "create.order.saga.0.inventory.compensable.transaction";
    String CREATE_ORDER_SAGA_COMMIT = "create.order.saga.0.commit";

    void callLocalTransaction(IMessage message);
    void callCompensatingTransaction(IMessage message);
    void callCommit(IMessage message);
}
