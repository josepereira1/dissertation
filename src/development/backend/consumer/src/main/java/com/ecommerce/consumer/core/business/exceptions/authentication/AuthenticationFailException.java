package com.ecommerce.consumer.core.business.exceptions.authentication;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class AuthenticationFailException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Client with id %s is not authenticated.";

    @Builder
    public AuthenticationFailException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
