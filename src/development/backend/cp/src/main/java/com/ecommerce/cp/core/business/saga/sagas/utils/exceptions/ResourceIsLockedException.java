package com.ecommerce.cp.core.business.saga.sagas.utils.exceptions;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class ResourceIsLockedException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Resource called %s, with id %s is locked.";

    @Builder
    public ResourceIsLockedException(String resourceName, String resourceId, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, resourceName, resourceId)));
    }
}
