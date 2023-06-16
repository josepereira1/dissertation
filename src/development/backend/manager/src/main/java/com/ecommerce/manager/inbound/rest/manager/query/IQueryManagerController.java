package com.ecommerce.manager.inbound.rest.manager.query;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RequestMapping(path = "/api/managers")
public interface IQueryManagerController {

    @GetMapping(path = "/{id}")
    ResponseEntity readManager(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @PathVariable(name = "id") String id) throws EcommerceBusinessLogicException;
}
