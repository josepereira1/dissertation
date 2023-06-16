package com.ecommerce.cc.core.business.exceptions.category;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class CategoryNotExistException extends EcommerceBusinessLogicException {

    private final static String defaultMessage = "Category %d not exists.";

    @Builder
    public CategoryNotExistException(Long id, String errorMessage) {
        super(Optional.ofNullable(errorMessage).orElse(String.format(defaultMessage, id)));
    }
}
