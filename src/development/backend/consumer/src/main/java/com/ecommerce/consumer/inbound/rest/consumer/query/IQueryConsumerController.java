package com.ecommerce.consumer.inbound.rest.consumer.query;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@CrossOrigin
@RequestMapping(path = "/api/consumers")
public interface IQueryConsumerController {

    @GetMapping(path = "/{id}")
    ResponseEntity readConsumer(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @PathVariable(name = "id") String id) throws EcommerceBusinessLogicException;
}
