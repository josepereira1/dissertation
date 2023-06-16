package com.ecommerce.cc.inbound.adapters.rest.product.query;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/products")
public interface IQueryProductController {

    @GetMapping(path = "/{id}/categories")
    ResponseEntity readProductCategories(@PathVariable(name = "id") String id) throws EcommerceBusinessLogicException;
}
