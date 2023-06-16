package com.ecommerce.cc.core.business.exceptions.category;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class CategoryNameIsInvalidException extends EcommerceBusinessLogicException {

    private static final String defaultMessage = "Name %s is invalid.";

    @Builder
    public CategoryNameIsInvalidException(String categoryName, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, categoryName)));
    }
}
