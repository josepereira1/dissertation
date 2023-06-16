package com.ecommerce.qcvp.core.business.exceptions.category;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class CategoryTreeIsInvalidException extends EcommerceBusinessLogicException {

    private static final String defaultMessage = "Category tree is invalid, root category should be named with <<#root>>.";

    @Builder
    public CategoryTreeIsInvalidException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
