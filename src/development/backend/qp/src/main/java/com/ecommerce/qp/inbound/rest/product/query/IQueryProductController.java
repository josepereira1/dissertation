package com.ecommerce.qp.inbound.rest.product.query;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@CrossOrigin
@RequestMapping(path = "/api/products")
public interface IQueryProductController {

    @GetMapping(path = "/{id}/visible")
    ResponseEntity readProduct(@Valid @NotBlank @PathVariable(name = "id") String id) throws EcommerceBusinessLogicException;

    @GetMapping(path = "/{id}")
    ResponseEntity readProductByVisibility(@Valid @NotBlank @PathVariable(name = "id") String id) throws EcommerceBusinessLogicException;

}
