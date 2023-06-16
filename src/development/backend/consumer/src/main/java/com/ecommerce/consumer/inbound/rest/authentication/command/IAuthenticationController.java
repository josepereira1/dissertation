package com.ecommerce.consumer.inbound.rest.authentication.command;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping(path = "/api/consumer")
public interface IAuthenticationController {

    @PostMapping(path = "/basic/auth")
    ResponseEntity basicAuth(@RequestHeader(name = "Authorization") String usernamePassword) throws EcommerceBusinessLogicException;

    @PostMapping(path = "/bearer/auth")
    ResponseEntity bearerAuth(@RequestHeader(name = "Authorization") String token) throws EcommerceBusinessLogicException;
}
