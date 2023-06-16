package com.ecommerce.cp.core.business.exceptions.product;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class SomeArgumentsRelatedToPriceInFaultException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Some arguments (%s) related to price in fault.";

    @Builder
    public SomeArgumentsRelatedToPriceInFaultException(String arguments, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, arguments)));
    }
}
