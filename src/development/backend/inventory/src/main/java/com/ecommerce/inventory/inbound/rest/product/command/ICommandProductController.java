package com.ecommerce.inventory.inbound.rest.product.command;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.inventory.inbound.rest.product.mappers.in.dtos.UpdateProductFeatureProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@CrossOrigin
@RequestMapping(path = "/api/products")
public interface ICommandProductController {

    @PatchMapping(path = "/{id}/stock")
    ResponseEntity updateProductStock(@Valid @NotBlank @PathVariable(name = "id") String id, @Valid @NotNull @RequestBody UpdateProductFeatureProductDTO product) throws EcommerceBusinessLogicException;
}
