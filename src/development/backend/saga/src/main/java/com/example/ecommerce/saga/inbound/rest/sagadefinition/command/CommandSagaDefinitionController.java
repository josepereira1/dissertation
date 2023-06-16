package com.example.ecommerce.saga.inbound.rest.sagadefinition.command;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import com.example.ecommerce.saga.core.ports.in.sagadefinition.ICommandSagaDefinition;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.ISagaDefinitionInMapper;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos.CreateSagaDefinitionSagaDefinitionDTO;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos.UpdateSagaDefinitionSagaDefinitionDTO;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.out.ISagaDefinitionOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
public class CommandSagaDefinitionController implements ICommandSagaDefinitionController{

    @Autowired
    private ICommandSagaDefinition commandSagaDefinition;

    @Override
    public ResponseEntity createSagaDefinition(CreateSagaDefinitionSagaDefinitionDTO sagaDefinition) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(ISagaDefinitionOutMapper.toSagaDefinitionDTO(commandSagaDefinition.createSagaDefinition(ISagaDefinitionInMapper.toSagaDefinition(sagaDefinition))));
    }

    @Override
    public ResponseEntity updateSagaDefinition(@Valid @NotBlank String id, @Valid @NotNull UpdateSagaDefinitionSagaDefinitionDTO sagaDefinition) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(ISagaDefinitionOutMapper.toSagaDefinitionDTO(commandSagaDefinition.updateSagaDefinition(id, ISagaDefinitionInMapper.toSagaDefinition(sagaDefinition))));
    }

    @Override
    public ResponseEntity deleteSagaDefinition(@Valid @NotBlank String id) throws EcommerceBusinessLogicException {
        commandSagaDefinition.deleteSagaDefinition(id);
        return new ResponseEntity("", HttpStatus.NO_CONTENT);
    }
}
