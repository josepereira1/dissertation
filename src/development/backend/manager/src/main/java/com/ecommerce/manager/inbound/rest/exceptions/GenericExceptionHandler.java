package com.ecommerce.manager.inbound.rest.exceptions;

import com.ecommerce.manager.inbound.rest.Response;
import com.ecommerce.manager.inbound.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GenericExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(Exception.class)
    public ResponseEntity exp(NestedServletException e) {
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.INTERNAL_SERVER_ERROR).message(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
