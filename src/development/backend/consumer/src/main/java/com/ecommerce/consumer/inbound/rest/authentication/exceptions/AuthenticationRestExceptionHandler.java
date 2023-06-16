package com.ecommerce.consumer.inbound.rest.authentication.exceptions;

import com.ecommerce.consumer.core.business.exceptions.authentication.AuthenticationFailException;
import com.ecommerce.consumer.inbound.rest.Response;
import com.ecommerce.consumer.inbound.rest.ResponseStatus;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class AuthenticationRestExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(AuthenticationFailException.class)
    public ResponseEntity exp(AuthenticationFailException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.AUTHENTICATION_FAIL).message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity exp(ExpiredJwtException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.UNAUTHORIZED).message("JWT expired!").build(), HttpStatus.UNAUTHORIZED);
    }
}
