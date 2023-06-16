package com.ecommerce.manager.inbound.rest.manager.exceptions;

import com.ecommerce.manager.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.manager.core.business.exceptions.manager.ManagerAlreadyExistsException;
import com.ecommerce.manager.inbound.rest.Response;
import com.ecommerce.manager.inbound.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class ManagerRestExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(ManagerAlreadyExistsException.class)
    public ResponseEntity exp(ManagerAlreadyExistsException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.EMPLOYEE_ALREADY_EXISTS).message(e.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity exp(UnauthorizedException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.UNAUTHORIZED).message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }
}
