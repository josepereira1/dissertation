package com.ecommerce.qp.core.business.saga.utils.exceptions;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class CountermeasureProblemException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Resource called %s, with id %s has a countermeasure problem.";

    @Builder
    public CountermeasureProblemException(String resourceName, String resourceId, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, resourceName, resourceId)));
    }
}
