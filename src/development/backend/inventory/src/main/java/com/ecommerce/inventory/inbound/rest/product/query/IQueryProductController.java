package com.ecommerce.inventory.inbound.rest.product.query;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping(path = "/api/products")
public interface IQueryProductController {

    @GetMapping(path = "/{id}/stock")
    ResponseEntity readProduct(@PathVariable(name = "id") String id) throws EcommerceBusinessLogicException;
}
