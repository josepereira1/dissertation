package com.ecommerce.qcap.core.business.exceptions.version;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class IsNotNextVersionException extends EcommerceBusinessLogicException {

    private static final String defaultMessage = "The %s version isn't next version.";

    @Builder
    public IsNotNextVersionException(String version, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, version)));
    }
}
