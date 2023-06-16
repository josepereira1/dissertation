package com.example.ecommerce.saga.inbound.rest.sagas.generic;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import com.example.ecommerce.saga.core.business.exceptions.auth.UnauthorizedException;
import com.example.ecommerce.saga.core.business.framework.logic.saga.command.SagaCoordinationFramework;
import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.HttpMethod;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.core.ports.in.sagadefinition.IQuerySagaDefinition;
import com.example.ecommerce.saga.inbound.rest.exceptions.MethodNotAllowedException;
import com.example.ecommerce.saga.inbound.rest.security.Authentication;
import com.example.ecommerce.saga.inbound.rest.security.Role;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.Claims;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

@RestController
public class GenericSagaController implements IGenericSagaController {

    @Autowired
    private SagaCoordinationFramework sagaCoordinationFramework;

    @Autowired
    private Authentication authentication;

    @Autowired
    private IQuerySagaDefinition querySagaDefinition;

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Override
    public ResponseEntity genericGet(@Valid @NotBlank String token, @Valid @NotBlank String id, @Valid String json) throws EcommerceBusinessLogicException, IOException {
        return ResponseEntity.ok(generic(token, id, json, HttpMethod.GET));
    }

    @Override
    public ResponseEntity genericPost(@Valid @NotBlank String token, @Valid @NotBlank String id, @Valid String json) throws EcommerceBusinessLogicException, IOException {
        return ResponseEntity.ok(generic(token, id, json, HttpMethod.POST));
    }

    @Override
    public ResponseEntity genericPut(@Valid @NotBlank String token, @Valid @NotBlank String id, @Valid String json) throws EcommerceBusinessLogicException, IOException {
        return ResponseEntity.ok(generic(token, id, json, HttpMethod.PUT));
    }

    @Override
    public ResponseEntity genericPatch(@Valid @NotBlank String token, @Valid @NotBlank String id, @Valid String json) throws EcommerceBusinessLogicException, IOException {
        return ResponseEntity.ok(generic(token, id, json, HttpMethod.PATCH));
    }

    @Override
    public ResponseEntity genericDelete(@Valid @NotBlank String token, @Valid @NotBlank String id, @Valid String json) throws EcommerceBusinessLogicException, IOException {
        return ResponseEntity.ok(generic(token, id, json, HttpMethod.DELETE));
    }

    private Saga generic(String token, String id, String json, HttpMethod method) throws EcommerceBusinessLogicException {
        SagaDefinition sagaDefinition = querySagaDefinition.readSagaDefinitionAuthenticationAndHttpMethodAndRolesAndJsonSchema(id);
        if(!sagaDefinition.getHttpMethod().equals(method)) throw MethodNotAllowedException.builder().method(method).build();
        Claims claims = authentication.authenticateAndGetClaims(token);
        if(!sagaDefinition.getRoles().contains(Role.valueOf(String.valueOf(claims.get("role"))))) throw UnauthorizedException.builder().build();
        if(sagaDefinition.getJsonSchema() != null) validateMessage(json, gson.toJson(sagaDefinition.getJsonSchema()));
        return sagaCoordinationFramework.initSaga(id, token, claims.getSubject(), json);
    }

    private void validateMessage(String json, String jsonSchema) throws ValidationException {
        SchemaLoader.load(new JSONObject(jsonSchema)).validate(new JSONObject(json));
    }
}
