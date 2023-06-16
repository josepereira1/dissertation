package com.example.ecommerce.saga.core.business.framework.exceptions;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class SagaWithThatIdNotExistsException extends EcommerceBusinessLogicException {
    private static String defaultMessage = "Saga with id %s not exists.";

    @Builder
    public SagaWithThatIdNotExistsException(String id, String errorMessage){
        super(Optional.ofNullable(errorMessage).orElse(String.format(defaultMessage, id)));
    }
}
