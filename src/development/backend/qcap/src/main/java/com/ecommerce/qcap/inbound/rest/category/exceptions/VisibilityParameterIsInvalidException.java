package com.ecommerce.qcap.inbound.rest.category.exceptions;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class VisibilityParameterIsInvalidException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "Visibility parameter is invalid (should be visible, not_visible or all).";

    @Builder
    public VisibilityParameterIsInvalidException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
