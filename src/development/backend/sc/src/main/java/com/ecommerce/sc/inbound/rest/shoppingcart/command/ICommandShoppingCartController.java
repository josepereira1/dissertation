package com.ecommerce.sc.inbound.rest.shoppingcart.command;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.inbound.rest.shoppingcart.mappers.in.dtos.UpdateShoppingCartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@RequestMapping(path = "/api/shoppingcarts")
public interface ICommandShoppingCartController {

    @PatchMapping(path = "/{id}")
    ResponseEntity createAndUpdateShoppingCart(@Valid @RequestHeader(name = "Authorization") String token, @Valid @PathVariable(name = "id") String id, @Valid @NotNull @RequestBody Set<UpdateShoppingCartDTO> products) throws EcommerceBusinessLogicException;

    @DeleteMapping(path = "/{id}/products")
    ResponseEntity deleteShoppingCartProducts(@Valid @RequestHeader(name = "Authorization") String token, @Valid @PathVariable(name = "id") String shoppingCartId) throws EcommerceBusinessLogicException;
}
