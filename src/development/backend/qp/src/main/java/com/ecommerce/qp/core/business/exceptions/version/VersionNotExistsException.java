package com.ecommerce.qp.core.business.exceptions.version;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import lombok.Builder;
import java.util.Optional;

public class VersionNotExistsException extends EcommerceBusinessLogicException {

    private static final String defaultMessage = "Version with groupId %s not exists.";

    @Builder
    public VersionNotExistsException(String groupId, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, groupId)));
    }
}
