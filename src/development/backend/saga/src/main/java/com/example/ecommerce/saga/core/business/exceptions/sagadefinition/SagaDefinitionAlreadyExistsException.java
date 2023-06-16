package com.example.ecommerce.saga.core.business.exceptions.sagadefinition;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class SagaDefinitionAlreadyExistsException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "SagaDefinition with id %s already exists.";

    @Builder
    public SagaDefinitionAlreadyExistsException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
