package com.ecommerce.manager.core.business.logic.auth.command;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.manager.core.business.logic.auth.command.features.BasicAuthentication;
import com.ecommerce.manager.core.business.logic.auth.command.features.BearerAuthentication;
import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.core.ports.in.auth.ICommandAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandAuthentication implements ICommandAuthentication {

    @Autowired
    private BasicAuthentication basicAuthentication;

    @Autowired
    private BearerAuthentication bearerAuthentication;

    @Override
    public Manager authenticate(String id, String password) throws EcommerceBusinessLogicException {
        return basicAuthentication.authenticate(id, password);
    }

    @Override
    public Manager authenticate(String token) throws EcommerceBusinessLogicException {
        return bearerAuthentication.authenticate(token);
    }
}
