package com.ecommerce.qcvp.inbound.rest.category.exceptions;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class PageAndProductsPerPageParametersMustBeGreaterThanZeroException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "<<page>> and/or <<products_per_page>> must be greater than zero.";

    @Builder
    public PageAndProductsPerPageParametersMustBeGreaterThanZeroException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
