package com.ecommerce.co.core.business.exceptions.order;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class CantCreateOrderWithouProductsException extends EcommerceBusinessLogicException {
    public final static String defaultMessage = "Can not create order without products.";

    @Builder
    public CantCreateOrderWithouProductsException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
