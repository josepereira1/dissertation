package com.ecommerce.cc.inbound.adapters.rest.product.command;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RequestMapping(path = "/api/products")
public interface ICommandProductController {

    @RequestMapping(path = "/{id}/categories", method = {RequestMethod.PATCH, RequestMethod.POST})
    ResponseEntity addCategories(@PathVariable(name = "id") String id, @RequestBody Set<Long> categoryIds) throws EcommerceBusinessLogicException;

    @DeleteMapping(path = "/{id}/categories")
    ResponseEntity removeCategories(@PathVariable(name = "id") String id, @RequestBody Set<Long> categoryIds) throws EcommerceBusinessLogicException;

}
