package com.ecommerce.sc.core.business.exceptions.shoppingcart;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ShoppingCartAlreadyExistsException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "Shopping cart with id %s already exists.";

    @Builder
    public ShoppingCartAlreadyExistsException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
