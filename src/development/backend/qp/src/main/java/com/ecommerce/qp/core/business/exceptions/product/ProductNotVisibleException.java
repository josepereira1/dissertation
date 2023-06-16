package com.ecommerce.qp.core.business.exceptions.product;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ProductNotVisibleException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Product with id %s is not visible.";

    @Builder
    public ProductNotVisibleException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
