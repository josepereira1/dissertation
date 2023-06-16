package com.ecommerce.inventory.core.ports.out.messaging.saga;

import com.ecommerce.inventory.core.business.messaging.resources.MessageStatus;

public interface ISagaOrchestrator {
    void replyToOrchestrator(String orchestratorName, MessageStatus messageStatus, Integer code, String message, Object metadata, Object data);
}
