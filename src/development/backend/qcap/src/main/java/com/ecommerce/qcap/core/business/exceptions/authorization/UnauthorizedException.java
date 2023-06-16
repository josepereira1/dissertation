package com.ecommerce.qcap.core.business.exceptions.authorization;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class UnauthorizedException extends EcommerceBusinessLogicException {
    public final static String defaultMessage = "Unauthorized.";

    @Builder
    public UnauthorizedException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
