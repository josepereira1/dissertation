package com.example.ecommerce.saga.core.business.exceptions.sagadefinition;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class SagaDefinitionNotExistsException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "SagaDefinition with id %s not exists.";

    @Builder
    public SagaDefinitionNotExistsException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
