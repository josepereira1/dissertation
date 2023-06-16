package com.ecommerce.consumer.inbound.rest.authentication.command;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.core.ports.in.authentication.ICommandAuthentication;
import com.ecommerce.consumer.inbound.rest.authentication.mappers.out.IAuthenticationOutMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.websocket.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements IAuthenticationController {

    @Autowired
    private ICommandAuthentication commandAuthentication;

    @Override
    public ResponseEntity basicAuth(String usernamePassword) throws EcommerceBusinessLogicException {
        String token = new String(new Base64().decode(usernamePassword.split("Basic")[1].getBytes()));
        Consumer consumer = commandAuthentication.authenticate(token.split(":")[0], token.split(":")[1]);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(Constants.AUTHORIZATION_HEADER_NAME, consumer.getToken());
        return ResponseEntity.ok().headers(httpHeaders).body(IAuthenticationOutMapper.toAuthenticateDTO(consumer));
    }

    @Override
    public ResponseEntity bearerAuth(String token) throws EcommerceBusinessLogicException {
        Consumer consumer = commandAuthentication.authenticate(token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(Constants.AUTHORIZATION_HEADER_NAME, consumer.getToken());
        return ResponseEntity.ok().headers(httpHeaders).body(IAuthenticationOutMapper.toAuthenticateDTO(consumer));
    }
}
