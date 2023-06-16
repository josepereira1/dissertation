package com.ecommerce.cc.inbound.adapters.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GenericExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity exp1(ConstraintViolationException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).status(ResponseStatus.ERROR).appCode(Response.BAD_REQUEST).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
