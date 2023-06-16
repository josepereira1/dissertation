package com.ecommerce.co.core.business.exceptions.util;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ParametersInFaultException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "Parameters [%s] in fault.";

    @Builder
    public ParametersInFaultException(String parameters, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, parameters)));
    }
}
