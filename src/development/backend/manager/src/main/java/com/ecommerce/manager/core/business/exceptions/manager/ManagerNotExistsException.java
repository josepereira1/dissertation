package com.ecommerce.manager.core.business.exceptions.manager;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ManagerNotExistsException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Employee with id %s not exists.";

    @Builder
    public ManagerNotExistsException(String id, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, id)));
    }
}
