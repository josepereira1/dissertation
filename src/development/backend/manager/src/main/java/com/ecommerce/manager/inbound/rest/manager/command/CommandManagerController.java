package com.ecommerce.manager.inbound.rest.manager.command;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.manager.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.manager.core.business.logic.auth.command.util.security.ISecurity;
import com.ecommerce.manager.core.ports.in.manager.ICommandManager;
import com.ecommerce.manager.inbound.rest.manager.mappers.in.IManagerInMapper;
import com.ecommerce.manager.inbound.rest.manager.mappers.in.dtos.CreateManagerDTO;
import com.ecommerce.manager.inbound.rest.manager.mappers.in.dtos.UpdateManagerDTO;
import com.ecommerce.manager.inbound.rest.manager.mappers.out.IManagerOutMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandManagerController implements ICommandManagerController {

    @Autowired
    private ICommandManager commandManager;

    @Autowired
    private ISecurity security;

    @Override
    public ResponseEntity createManager(CreateManagerDTO manager) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(IManagerOutMapper.toIdAndEmailAndTokenDTO(commandManager.createManager(IManagerInMapper.toEmployee(manager))));
    }

    @Override
    public ResponseEntity updateManager(String token, String id, UpdateManagerDTO manager) throws EcommerceBusinessLogicException{
        Claims claims = security.authenticate(token);
        if(!claims.getSubject().equals(id)) throw UnauthorizedException.builder().build();
        return ResponseEntity.ok(IManagerOutMapper.toIdAndEmailDTO(commandManager.updateManager(id, IManagerInMapper.toEmployee(manager))));
    }
}
