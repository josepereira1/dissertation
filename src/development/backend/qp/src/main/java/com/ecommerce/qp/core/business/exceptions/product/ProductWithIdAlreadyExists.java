package com.ecommerce.qp.core.business.exceptions.product;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ProductWithIdAlreadyExists extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Product with id %s already exists.";

    @Builder
    public ProductWithIdAlreadyExists(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
