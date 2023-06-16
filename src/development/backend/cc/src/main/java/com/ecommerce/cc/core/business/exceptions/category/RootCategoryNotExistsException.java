package com.ecommerce.cc.core.business.exceptions.category;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class RootCategoryNotExistsException extends EcommerceBusinessLogicException {
    public final static String defaultMessage = "Root category with name %s not exists.";

    @Builder
    public RootCategoryNotExistsException(String categoryName, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, categoryName)));
    }
}
