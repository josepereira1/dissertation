package com.ecommerce.qp.inbound.messaging.version.exceptions;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class MaximumVersionCheckAttemptsExceededException extends EcommerceBusinessLogicException {
    private static final String defaultMessage = "Maximum version attempts (%d) exceeded for the version with groupId %s.";

    @Builder
    public MaximumVersionCheckAttemptsExceededException(String groupId, Integer attempts, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, attempts, groupId)));
    }
}
