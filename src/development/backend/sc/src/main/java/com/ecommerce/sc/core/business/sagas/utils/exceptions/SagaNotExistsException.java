package com.ecommerce.sc.core.business.sagas.utils.exceptions;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class SagaNotExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Saga with name %s and id %s is invalid.";

    @Builder
    public SagaNotExistsException(String name, String sagaId, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, name, sagaId)));
    }
}
