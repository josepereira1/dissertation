package com.ecommerce.sc.inbound.rest.shoppingcart.query;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RequestMapping(path = "/api/shoppingcarts")
public interface IQueryShoppingCartController {

    @GetMapping("/{id}")
    ResponseEntity readShoppingCart(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @PathVariable(name = "id") String id) throws EcommerceBusinessLogicException;

    @GetMapping("/{id}/size")
    ResponseEntity readShoppingCartSize(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @PathVariable(name = "id") String id) throws EcommerceBusinessLogicException;
}
