package com.example.ecommerce.saga.inbound.rest.saga.query;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@CrossOrigin
@RequestMapping(path = "/api/sagas")
public interface IQuerySagaController {

    @GetMapping
    ResponseEntity getSagas(@Valid @NotBlank @RequestHeader(name = "Authorization") String token);

    @GetMapping(path = "/{id}")
    ResponseEntity getSaga(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @PathVariable(name = "id") String id) throws EcommerceBusinessLogicException;
}
