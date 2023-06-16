package com.ecommerce.manager.core.business.exceptions.authentication;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class AuthenticationFailException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Employee with id %s is unauthorized.";

    @Builder
    public AuthenticationFailException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
