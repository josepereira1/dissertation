package com.ecommerce.manager.inbound.rest.manager.query;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.manager.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.manager.core.business.logic.auth.command.util.security.ISecurity;
import com.ecommerce.manager.core.ports.in.manager.IQueryManager;
import com.ecommerce.manager.inbound.rest.manager.mappers.out.IManagerOutMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryManagerController implements IQueryManagerController {

    @Autowired
    private IQueryManager queryManager;

    @Autowired
    private ISecurity security;

    @Override
    public ResponseEntity readManager(String token, String id) throws EcommerceBusinessLogicException {
        Claims claims = security.authenticate(token);
        if(!claims.getSubject().equals(id)) throw UnauthorizedException.builder().build();
        return ResponseEntity.ok(IManagerOutMapper.toIdAndEmailDTO(queryManager.readManager(id)));
    }
}
