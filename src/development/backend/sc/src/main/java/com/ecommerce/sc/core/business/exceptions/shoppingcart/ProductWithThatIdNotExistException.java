package com.ecommerce.sc.core.business.exceptions.shoppingcart;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;

import java.util.Optional;

public class ProductWithThatIdNotExistException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "Product with id %s not exists.";

    @Builder
    public ProductWithThatIdNotExistException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
