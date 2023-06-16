package com.ecommerce.qcap.core.business.exceptions.category;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ProductAlreadyExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Product with id %s already exists.";

    @Builder
    public ProductAlreadyExistsException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
