package com.ecommerce.qcap.core.business.exceptions.category;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class RootCategoryAlreadyExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Root category already exists.";

    @Builder
    public RootCategoryAlreadyExistsException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
