package com.ecommerce.co.inbound.rest.order.command;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.co.core.business.resources.OrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@CrossOrigin
@RequestMapping(path = "/api/orders")
public interface ICommandOrderController {

    @PatchMapping(path = "/{id}")
    ResponseEntity updateOrderStatus(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @PathVariable(name = "id") String id, @Valid @NotNull @RequestParam(name = "status") OrderStatus status) throws EcommerceBusinessLogicException;

    @DeleteMapping(path = "/{id}")
    ResponseEntity deleteOrder(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @PathVariable(name = "id") String id) throws EcommerceBusinessLogicException;
}
