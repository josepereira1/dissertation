package com.ecommerce.inventory.core.business.saga.utils;

import com.ecommerce.inventory.core.business.saga.utils.exceptions.SagaIdNotExistsInMessageException;
import com.ecommerce.inventory.core.business.saga.utils.resources.SagaMetadata;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CommonSaga {

    public static SagaMetadata getSagaMetadata(JsonElement metadata) throws SagaIdNotExistsInMessageException {
        SagaMetadata sagaMetadata = new GsonBuilder().create().fromJson(metadata, SagaMetadata.class);
        if(sagaMetadata.getSagaId() == null
                || (sagaMetadata.getSagaId() != null && sagaMetadata.getSagaId().equals("")))
            throw SagaIdNotExistsInMessageException.builder().build();
        return sagaMetadata;
    }
}
