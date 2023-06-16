package com.ecommerce.qcvp.core.business.exceptions.category;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class CategoryNotExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Category with id %d not exists.";

    @Builder
    public CategoryNotExistsException(Long id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
