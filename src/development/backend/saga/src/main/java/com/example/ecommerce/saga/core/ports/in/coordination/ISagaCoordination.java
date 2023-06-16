package com.example.ecommerce.saga.core.ports.in.coordination;

import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.SagaDefinitionNotExistsException;
import java.io.IOException;

public interface ISagaCoordination {
    void handleMessage(String json) throws IOException, SagaDefinitionNotExistsException;
}
