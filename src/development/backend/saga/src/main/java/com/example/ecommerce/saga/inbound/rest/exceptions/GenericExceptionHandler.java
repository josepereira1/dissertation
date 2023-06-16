package com.example.ecommerce.saga.inbound.rest.exceptions;

import com.example.ecommerce.saga.core.business.exceptions.auth.UnauthorizedException;
import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.BadDefinitionException;
import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.SagaDefinitionAlreadyExistsException;
import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.SagaDefinitionNotExistsException;
import com.example.ecommerce.saga.core.business.framework.exceptions.SagaWithThatIdNotExistsException;
import com.example.ecommerce.saga.core.business.logs.ILogs;
import com.example.ecommerce.saga.inbound.rest.Response;
import com.example.ecommerce.saga.inbound.rest.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.SignatureException;
import org.everit.json.schema.ValidationException;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity exp(Exception e){
        e.printStackTrace();
        logs.logError(e.getMessage() != null ? e.getMessage() : "Server internal error.");
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.INTERNAL_SERVER_ERROR).message(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SagaWithThatIdNotExistsException.class)
    public ResponseEntity exp(SagaWithThatIdNotExistsException e){
        e.printStackTrace();
        logs.logError(e.getMessage() != null ? e.getMessage() : "Saga not exists.");
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.SAGA_NOT_EXISTS_EXCEPTION).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity exp(UnauthorizedException e){
        e.printStackTrace();
        logs.logError(e.getMessage() != null ? e.getMessage() : "Unauthorized.");
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.UNAUTHORIZED).message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SagaDefinitionNotExistsException.class)
    public ResponseEntity exp(SagaDefinitionNotExistsException e){
        e.printStackTrace();
        logs.logError(e.getMessage() != null ? e.getMessage() : "Saga definition not exists.");
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.SAGA_DEFINITION_NOT_EXISTS_EXCEPTION).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SagaDefinitionAlreadyExistsException.class)
    public ResponseEntity exp(SagaDefinitionAlreadyExistsException e){
        e.printStackTrace();
        logs.logError(e.getMessage() != null ? e.getMessage() : "Saga definition already exists.");
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.SAGA_DEFINITION_NOT_EXISTS_EXCEPTION).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadDefinitionException.class)
    public ResponseEntity exp(BadDefinitionException e){
        e.printStackTrace();
        logs.logError(e.getMessage() != null ? e.getMessage() : "Bad definition.");
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.BAD_DEFINITION_EXCEPTION).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity exp(JsonProcessingException e){
        e.printStackTrace();
        logs.logError(e.getMessage() != null ? e.getMessage() : "Json is invalid.");
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.SAGA_NOT_EXISTS_EXCEPTION).message(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity exp(ValidationException e){
        e.printStackTrace();
        logs.logError(e.getMessage() != null ? e.getMessage() : "Json is invalid.");
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.BAD_REQUEST).message("Json is invalid.").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity exp(SignatureException e){
        e.printStackTrace();
        logs.logError(e.getMessage() != null ? e.getMessage() : "Unauthorized.");
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.UNAUTHORIZED).message("Unauthorized").build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity exp(MethodNotAllowedException e){
        e.printStackTrace();
        logs.logError(e.getMessage() != null ? e.getMessage() : "Method not allowed.");
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.METHOD_NOT_ALLOWED_HERE_EXCEPTION).message(e.getMessage()).build(), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
