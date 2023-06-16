package com.ecommerce.inventory.core.business.exceptions.product;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ProductAlreadyExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Product already exists.";

    @Builder
    public ProductAlreadyExistsException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
