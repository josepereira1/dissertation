package com.ecommerce.consumer.core.business.exceptions.consumer;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ConsumerNotExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Consumer with id %s not exists.";

    @Builder
    public ConsumerNotExistsException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
