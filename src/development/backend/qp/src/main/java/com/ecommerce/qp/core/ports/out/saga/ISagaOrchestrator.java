package com.ecommerce.qp.core.ports.out.saga;

import com.ecommerce.qp.core.business.messaging.resources.MessageStatus;

public interface ISagaOrchestrator {
    void replyToOrchestrator(String orchestratorName, MessageStatus messageStatus, Integer code, String message, Object metadata, Object data);
}
