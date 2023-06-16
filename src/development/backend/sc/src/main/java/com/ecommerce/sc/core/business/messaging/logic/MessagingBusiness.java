package com.ecommerce.sc.core.business.messaging.logic;

import com.ecommerce.sc.core.business.messaging.mappers.MessageDeserializer;
import com.ecommerce.sc.core.business.messaging.mappers.MessageSerializer;
import com.ecommerce.sc.core.business.messaging.resources.IMessage;
import com.ecommerce.sc.core.business.messaging.resources.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Getter
@Setter
@Service
public class MessagingBusiness implements IMessagingBusiness {

    @Value("${message.schema}")
    public String message;

    @Autowired
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Message.class, new MessageSerializer())
            .registerTypeAdapter(Message.class, new MessageDeserializer())
            .create();

    @Override
    public IMessage fromJson(String json) throws IOException {
        validateMessage(json);
        //  TODO aqui tb podemos vir a validar o data
        return gson.fromJson(json, Message.class);
    }

    @Override
    public String toJson(IMessage message){
        return gson.toJson(message);
    }

    private void validateMessage(String message) throws IOException, ValidationException {
        JSONObject jsonSchema = new JSONObject(new String(Files.readAllBytes(Paths.get(this.message))));
        SchemaLoader.load(jsonSchema).validate(new JSONObject(message));
    }
}
