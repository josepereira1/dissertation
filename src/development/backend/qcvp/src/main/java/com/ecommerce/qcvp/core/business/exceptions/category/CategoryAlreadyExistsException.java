package com.ecommerce.qcvp.core.business.exceptions.category;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class CategoryAlreadyExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Category already exists.";

    @Builder
    public CategoryAlreadyExistsException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
