package com.ecommerce.consumer.core.business.logic.authentication.command;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.consumer.core.business.logic.authentication.command.features.BasicAuthenticate;
import com.ecommerce.consumer.core.business.logic.authentication.command.features.BearerAuthenticate;
import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.core.ports.in.authentication.ICommandAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandAuthentication implements ICommandAuthentication {

    @Autowired
    private BasicAuthenticate basicAuthenticate;

    @Autowired
    private BearerAuthenticate bearerAuthenticate;

    @Override
    public Consumer authenticate(String id, String password) throws EcommerceBusinessLogicException {
        return basicAuthenticate.authentication(id, password);
    }

    @Override
    public Consumer authenticate(String token) throws EcommerceBusinessLogicException {
        return bearerAuthenticate.authenticate(token);
    }
}
