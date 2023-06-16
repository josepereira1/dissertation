package com.ecommerce.sc.core.business.sagas.utils;

import com.ecommerce.sc.core.business.sagas.utils.exceptions.SagaIdNotExistsInMessageException;
import com.ecommerce.sc.core.business.sagas.utils.resources.SagaMetadata;
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
