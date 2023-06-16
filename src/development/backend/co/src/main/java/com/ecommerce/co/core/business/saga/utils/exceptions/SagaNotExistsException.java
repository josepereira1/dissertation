package com.ecommerce.co.core.business.saga.utils.exceptions;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class SagaNotExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Saga with name %s and id %s not exists.";

    @Builder
    public SagaNotExistsException(String name, String sagaId, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, name, sagaId)));
    }
}
