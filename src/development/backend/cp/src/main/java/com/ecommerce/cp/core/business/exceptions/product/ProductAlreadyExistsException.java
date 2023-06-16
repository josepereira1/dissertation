package com.ecommerce.cp.core.business.exceptions.product;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ProductAlreadyExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Product with id or sku or ean or pn already exists.";

    @Builder
    public ProductAlreadyExistsException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
