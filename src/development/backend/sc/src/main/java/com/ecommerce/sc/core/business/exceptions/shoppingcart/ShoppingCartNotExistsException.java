package com.ecommerce.sc.core.business.exceptions.shoppingcart;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ShoppingCartNotExistsException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "Shopping cart with id %s not exists.";

    @Builder
    public ShoppingCartNotExistsException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
