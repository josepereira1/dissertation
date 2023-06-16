package com.example.ecommerce.saga.inbound.rest.sagadefinition.command;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos.CreateSagaDefinitionSagaDefinitionDTO;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos.UpdateSagaDefinitionSagaDefinitionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@CrossOrigin
@RequestMapping(path = "/api/sagasdefinitions")
public interface ICommandSagaDefinitionController {

    @PostMapping
    ResponseEntity createSagaDefinition(@Valid @NotNull @RequestBody CreateSagaDefinitionSagaDefinitionDTO sagaDefinition) throws EcommerceBusinessLogicException;

    @PutMapping(path = "/{id}")
    ResponseEntity updateSagaDefinition(@Valid @NotBlank @PathVariable(name = "id") String id, @Valid @NotNull @RequestBody UpdateSagaDefinitionSagaDefinitionDTO sagaDefinition) throws EcommerceBusinessLogicException;

    @DeleteMapping(path = "/{id}")
    ResponseEntity deleteSagaDefinition(@Valid @NotBlank @PathVariable(name = "id") String id) throws EcommerceBusinessLogicException;
}
