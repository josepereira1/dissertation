package com.ecommerce.co.inbound.rest.order.query;

import com.ecommerce.co.inbound.rest.order.mappers.in.dtos.OrderFilterParameterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@CrossOrigin
@RequestMapping(path = "/api/orders")
public interface IQueryOrderController {

    @GetMapping(path = "/{id}")
    ResponseEntity readOrder(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @PathVariable(name = "id") String id) throws Exception;

    @GetMapping(params = {"status"})
    ResponseEntity readOrdersByStatus(@Valid @NotBlank @RequestParam(name = "status") OrderFilterParameterDTO status);

    @GetMapping(params = {"clientId"})
    ResponseEntity readClientOrders(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @RequestParam(name = "clientId") String clientId) throws Exception;
}
