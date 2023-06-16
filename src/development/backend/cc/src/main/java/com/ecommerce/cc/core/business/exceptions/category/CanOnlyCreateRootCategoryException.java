package com.ecommerce.cc.core.business.exceptions.category;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class CanOnlyCreateRootCategoryException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "Can only create <<#root>> category, %s isn't valid name.";

    @Builder
    public CanOnlyCreateRootCategoryException(String categoryName, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, categoryName)));
    }
}
