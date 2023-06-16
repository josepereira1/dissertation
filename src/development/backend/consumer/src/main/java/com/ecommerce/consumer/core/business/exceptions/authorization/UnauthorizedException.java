package com.ecommerce.consumer.core.business.exceptions.authorization;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class UnauthorizedException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Unauthorized.";

    @Builder
    public UnauthorizedException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
