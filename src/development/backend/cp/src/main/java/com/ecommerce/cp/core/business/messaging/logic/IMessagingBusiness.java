package com.ecommerce.cp.core.business.messaging.logic;

import com.ecommerce.cp.core.business.messaging.resources.IMessage;
import com.google.gson.Gson;

import java.io.IOException;

public interface IMessagingBusiness {
    Gson getGson();
    IMessage fromJson(String json) throws IOException;
    String toJson(IMessage message);
}
