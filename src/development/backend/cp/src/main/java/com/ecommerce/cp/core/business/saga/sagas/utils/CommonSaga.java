package com.ecommerce.cp.core.business.saga.sagas.utils;

import com.ecommerce.cp.core.business.saga.sagas.utils.exceptions.SagaIdNotExistsInMessageException;
import com.ecommerce.cp.core.business.saga.sagas.utils.resources.SagaMetadata;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class CommonSaga {

    public static SagaMetadata getSagaMetadata(JsonElement metadata) throws SagaIdNotExistsInMessageException {
        SagaMetadata sagaMetadata = new GsonBuilder().create().fromJson(metadata, SagaMetadata.class);
        if(sagaMetadata.getSagaId() == null
                || (sagaMetadata.getSagaId() != null && sagaMetadata.getSagaId().equals("")))
            throw SagaIdNotExistsInMessageException.builder().build();
        return sagaMetadata;
    }
}
