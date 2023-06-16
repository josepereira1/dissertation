package com.ecommerce.manager.core.ports.in.auth;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.manager.core.business.resources.Manager;

public interface ICommandAuthentication {
    Manager authenticate(String id, String password) throws EcommerceBusinessLogicException;
    Manager authenticate(String token) throws EcommerceBusinessLogicException;
}
