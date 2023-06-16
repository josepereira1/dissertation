package com.ecommerce.manager.inbound.rest.authentication.command;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/manager")
public interface IAuthenticationController {

    @PostMapping(path = "/basic/auth")
    ResponseEntity basicAuth(@RequestHeader(name = "Authorization") String usernameAndPassword) throws EcommerceBusinessLogicException;

    @PostMapping(path = "/bearer/auth")
    ResponseEntity bearerAuth(@RequestHeader(name = "Authorization") String token) throws EcommerceBusinessLogicException;

}
