package com.ecommerce.sc.core.business.exceptions.shoppingcart;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;

import java.util.Optional;

public class ProductWithIdAlreadyExistsException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "Product with id %s already exists.";

    @Builder
    public ProductWithIdAlreadyExistsException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
