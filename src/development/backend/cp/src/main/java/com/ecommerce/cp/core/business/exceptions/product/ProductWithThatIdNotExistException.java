package com.ecommerce.cp.core.business.exceptions.product;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ProductWithThatIdNotExistException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Product with id %s not exists.";

    @Builder
    public ProductWithThatIdNotExistException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
