package com.ecommerce.manager.inbound.rest.manager.command;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.manager.inbound.rest.manager.mappers.in.dtos.CreateManagerDTO;
import com.ecommerce.manager.inbound.rest.manager.mappers.in.dtos.UpdateManagerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@CrossOrigin
@RequestMapping(path = "/api/managers")
public interface ICommandManagerController {

    @PostMapping
    ResponseEntity createManager(@Valid @NotNull @RequestBody CreateManagerDTO employee) throws EcommerceBusinessLogicException;

    @PutMapping(path = "/{id}")
    ResponseEntity updateManager(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @PathVariable(name = "id") String id, @Valid @NotNull @RequestBody UpdateManagerDTO manager) throws EcommerceBusinessLogicException;
}
