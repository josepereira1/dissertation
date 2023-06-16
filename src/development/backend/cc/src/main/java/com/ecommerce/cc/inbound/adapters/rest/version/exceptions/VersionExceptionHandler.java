package com.ecommerce.cc.inbound.adapters.rest.version.exceptions;

import com.ecommerce.cc.core.business.exceptions.version.VersionAlreadyExistsException;
import com.ecommerce.cc.core.business.exceptions.version.VersionNotExistsException;
import com.ecommerce.cc.inbound.adapters.rest.Response;
import com.ecommerce.cc.inbound.adapters.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class VersionExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(VersionAlreadyExistsException.class)
    public ResponseEntity exp(VersionAlreadyExistsException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.VERSION_ALREADY_EXISTS).message(e.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(VersionNotExistsException.class)
    public ResponseEntity exp(VersionNotExistsException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.VERSION_NOT_EXISTS).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }
}
