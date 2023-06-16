package com.ecommerce.co.core.ports.out.saga;

import com.ecommerce.co.core.business.messaging.resources.MessageStatus;

public interface ISagaOrchestrator {
    void replyToOrchestrator(String orchestratorName, MessageStatus messageStatus, Integer code, String message, Object metadata, Object data);
}
