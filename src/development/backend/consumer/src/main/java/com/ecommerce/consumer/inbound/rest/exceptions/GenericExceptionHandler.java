package com.ecommerce.consumer.inbound.rest.exceptions;

import com.ecommerce.consumer.inbound.rest.Response;
import com.ecommerce.consumer.inbound.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GenericExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(Exception.class)
    public ResponseEntity exp(Exception e) {
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.INTERNAL_SERVER_ERROR).message("Internal server error").build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
