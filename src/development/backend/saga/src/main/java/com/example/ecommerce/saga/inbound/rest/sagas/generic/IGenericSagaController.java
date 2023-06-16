package com.example.ecommerce.saga.inbound.rest.sagas.generic;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

@CrossOrigin
@RequestMapping(path = "/api/processes")
public interface IGenericSagaController {

    @GetMapping
    ResponseEntity genericGet(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @RequestParam(name = "id") String id, @Valid @RequestBody String json) throws EcommerceBusinessLogicException, IOException;

    @PostMapping
    ResponseEntity genericPost(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @RequestParam(name = "id") String id, @Valid @RequestBody String json) throws EcommerceBusinessLogicException, IOException;

    @PutMapping
    ResponseEntity genericPut(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @RequestParam(name = "id") String id, @Valid @RequestBody String json) throws EcommerceBusinessLogicException, IOException;

    @PatchMapping
    ResponseEntity genericPatch(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @RequestParam(name = "id") String id, @Valid @RequestBody String json) throws EcommerceBusinessLogicException, IOException;

    @DeleteMapping
    ResponseEntity genericDelete(@Valid @NotBlank @RequestHeader(name = "Authorization") String token, @Valid @NotBlank @RequestParam(name = "id") String id, @Valid @RequestBody String json) throws EcommerceBusinessLogicException, IOException;

}
