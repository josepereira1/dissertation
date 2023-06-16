package com.ecommerce.qcap.core.business.exceptions.version;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class VersionAlreadyExistsException extends EcommerceBusinessLogicException {

    private static final String defaultMessage = "Version with groupId %s already exists.";

    @Builder
    public VersionAlreadyExistsException(String groupId, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, groupId)));
    }
}
