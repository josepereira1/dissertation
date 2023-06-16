package com.ecommerce.qct.core.business.exceptions.tree;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class CategoryTreeNotExistsException extends EcommerceBusinessLogicException {

    private static final String defaultMessage = "Category tree not exists.";

    @Builder
    public CategoryTreeNotExistsException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
