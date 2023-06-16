package com.ecommerce.qp.inbound.messaging.messaging.consumer;

import com.ecommerce.qp.core.business.logs.ILogs;
import com.ecommerce.qp.core.business.messaging.logic.IMessagingBusiness;
import com.ecommerce.qp.core.business.messaging.resources.IMessage;
import org.everit.json.schema.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

public abstract class MessageHandler {

    @Autowired
    private IMessagingBusiness messagingBusiness;

    @Autowired
    private ILogs logs;

    public void handleMessage(String json){
        logs.logInfo("[MESSAGE_RECEIVED] - message: " + json);
        try{
            IMessage message;
            try {
                message = messagingBusiness.fromJson(json);
            } catch (ValidationException e){
                e.printStackTrace();
                logs.logError("Message received throw a json validator exception - message: " + json);
                return;
            } catch (IOException e) {
                e.printStackTrace();
                logs.logError(e.getMessage() + " - message: " + json);
                return;
            }
            callMethod(message.getMethod(), message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError(e.getMessage() + " - message: " + json);
            return;
        }
        return;
    }

    public abstract void callMethod(String method, IMessage message) throws Exception;
}
