package com.ecommerce.sc.inbound.rest.exceptions;

import com.ecommerce.sc.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.sc.core.business.exceptions.util.ParametersInFaultException;
import com.ecommerce.sc.core.business.logs.ILogs;
import com.ecommerce.sc.inbound.rest.Response;
import com.ecommerce.sc.inbound.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ILogs logs;

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity exp(UnauthorizedException e){
        e.printStackTrace();
        logs.logError(e.getMessage());
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.UNAUTHORIZED).message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ParametersInFaultException.class)
    public ResponseEntity exp(ParametersInFaultException e){
        e.printStackTrace();
        logs.logError(e.getMessage());
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.PARAMETERS_IN_FAULT).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
