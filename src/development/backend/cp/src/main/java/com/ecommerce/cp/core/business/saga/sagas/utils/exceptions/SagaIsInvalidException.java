package com.ecommerce.cp.core.business.saga.sagas.utils.exceptions;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class SagaIsInvalidException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Saga with name %s and id %s is invalid.";

    @Builder
    public SagaIsInvalidException(String name, String sagaId, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, name, sagaId)));
    }
}
