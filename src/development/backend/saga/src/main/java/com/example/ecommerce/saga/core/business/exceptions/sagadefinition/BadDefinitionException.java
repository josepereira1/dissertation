package com.example.ecommerce.saga.core.business.exceptions.sagadefinition;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class BadDefinitionException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "Bad definition.";

    @Builder
    public BadDefinitionException(String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(defaultMessage));
    }
}
