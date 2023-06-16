package com.ecommerce.cc.core.business.exceptions.product;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ProductWithIdOrPnAlreadyExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Product with id %s or pn %s already exists.";

    @Builder
    public ProductWithIdOrPnAlreadyExistsException(String id, String pn, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id, pn)));
    }
}
