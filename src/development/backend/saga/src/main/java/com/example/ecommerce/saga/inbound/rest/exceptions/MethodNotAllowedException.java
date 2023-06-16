package com.example.ecommerce.saga.inbound.rest.exceptions;

import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.HttpMethod;
import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import java.util.Optional;
import lombok.Builder;

public class MethodNotAllowedException extends EcommerceBusinessLogicException {

    public final static String defaultMessage = "Http method %s not allowed here.";

    @Builder
    public MethodNotAllowedException(HttpMethod method, String exceptionMessage){
        super(Optional.ofNullable(exceptionMessage).orElse(String.format(defaultMessage, method.name())));
    }
}
