package com.ecommerce.manager.inbound.rest.authentication.command;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.core.ports.in.auth.ICommandAuthentication;
import com.ecommerce.manager.inbound.rest.authentication.mappers.out.IAuthOutMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.websocket.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements IAuthenticationController {

    @Autowired
    private ICommandAuthentication commandAuthentication;

    @Override
    public ResponseEntity basicAuth(@RequestHeader(name = "Authorization") String usernameAndPassword) throws EcommerceBusinessLogicException {
        String basiAuth = new String(new Base64().decode(usernameAndPassword.split("Basic")[1].getBytes()));
        Manager manager = commandAuthentication.authenticate(basiAuth.split(":")[0], basiAuth.split(":")[1]);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(Constants.AUTHORIZATION_HEADER_NAME, manager.getToken());
        return ResponseEntity.ok().headers(httpHeaders).body(IAuthOutMapper.toAuthenticateDTO(manager));
    }

    @Override
    public ResponseEntity bearerAuth(@RequestHeader(name = "Authorization") String token) throws EcommerceBusinessLogicException{
        Manager manager = commandAuthentication.authenticate(token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(Constants.AUTHORIZATION_HEADER_NAME, manager.getToken());
        return ResponseEntity.ok().headers(httpHeaders).body(IAuthOutMapper.toAuthenticateDTO(manager));
    }
}
