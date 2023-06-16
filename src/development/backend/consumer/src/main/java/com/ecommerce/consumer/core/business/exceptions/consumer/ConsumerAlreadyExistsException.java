package com.ecommerce.consumer.core.business.exceptions.consumer;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ConsumerAlreadyExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Consumer already exists with id %s.";

    @Builder
    public ConsumerAlreadyExistsException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
