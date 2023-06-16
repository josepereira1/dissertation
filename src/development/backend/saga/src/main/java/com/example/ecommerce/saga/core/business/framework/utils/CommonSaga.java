package com.example.ecommerce.saga.core.business.framework.utils;

import com.example.ecommerce.saga.core.business.framework.resources.saga.SagaMetadata;
import com.example.ecommerce.saga.core.business.framework.utils.exceptions.SagaIdNotExistsInMessageException;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CommonSaga {

    public static String getSagaId(JsonElement data) throws SagaIdNotExistsInMessageException {
        JsonObject jo = new GsonBuilder().create().fromJson(data, JsonObject.class);
        if(jo.has("sagaId")) return jo.get("sagaId").getAsString();
        else throw SagaIdNotExistsInMessageException.builder().build();
    }

    public static SagaMetadata getSagaMetadata(JsonElement metadata) throws SagaIdNotExistsInMessageException {
        SagaMetadata sagaMetadata = new GsonBuilder().create().fromJson(metadata, SagaMetadata.class);
        if(sagaMetadata.getSagaId() == null
                || (sagaMetadata.getSagaId() != null && sagaMetadata.getSagaId().equals("")))
            throw SagaIdNotExistsInMessageException.builder().build();
        return sagaMetadata;
    }
}
