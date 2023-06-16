package com.ecommerce.co.core.business.exceptions.order;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class OrderNotExistsException extends EcommerceBusinessLogicException {
    public final static String defaultMessage = "Order with id %s not exists.";

    @Builder
    public OrderNotExistsException(String orderId, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, orderId)));
    }
}
