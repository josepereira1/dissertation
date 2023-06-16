package com.ecommerce.manager.core.business.exceptions.manager;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ManagerAlreadyExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Employee already exists with id %s.";

    @Builder
    public ManagerAlreadyExistsException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
