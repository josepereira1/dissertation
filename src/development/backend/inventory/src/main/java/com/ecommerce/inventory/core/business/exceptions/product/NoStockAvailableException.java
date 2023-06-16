package com.ecommerce.inventory.core.business.exceptions.product;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class NoStockAvailableException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "No stock available for product with id %s.";

    @Builder
    public NoStockAvailableException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
