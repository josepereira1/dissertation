package com.ecommerce.consumer.core.ports.in.authentication;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.consumer.core.business.resources.Consumer;

public interface ICommandAuthentication {
    Consumer authenticate(String id, String password) throws EcommerceBusinessLogicException;
    Consumer authenticate(String token) throws EcommerceBusinessLogicException;
}
