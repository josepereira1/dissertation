package com.ecommerce.cp.core.business.saga.sagas.utils.exceptions;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class SagaIdNotExistsInMessageException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Saga id not exists in message.";

    @Builder
    public SagaIdNotExistsInMessageException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
