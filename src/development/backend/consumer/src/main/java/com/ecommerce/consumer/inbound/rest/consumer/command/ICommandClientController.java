package com.ecommerce.consumer.inbound.rest.consumer.command;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos.CreateConsumerFeatureClientDTO;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos.UpdateConsumerFeatureClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@CrossOrigin
@RequestMapping(path = "/api/consumers")
public interface ICommandClientController {

    @PostMapping
    ResponseEntity createConsumer(@Valid @NotNull @RequestBody CreateConsumerFeatureClientDTO consumer) throws EcommerceBusinessLogicException;

    @PutMapping(path = "/{id}")
    ResponseEntity updateConsumer(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @PathVariable(name = "id") String id, @Valid @NotNull @RequestBody UpdateConsumerFeatureClientDTO consumer) throws EcommerceBusinessLogicException;
}
