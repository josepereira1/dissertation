package com.ecommerce.cc.core.business.exceptions.category;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class CategoryAlreadyExistException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "Category with name %s already exists.";

    @Builder
    public CategoryAlreadyExistException(String categoryName, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, categoryName)));
    }
}
