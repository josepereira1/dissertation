package com.ecommerce.manager.core.business.logic.manager.command.features;

import com.ecommerce.manager.core.business.exceptions.manager.ManagerAlreadyExistsException;
import com.ecommerce.manager.core.business.logic.auth.command.util.security.JwtProvider;
import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.core.business.resources.Roles;
import com.ecommerce.manager.core.ports.out.repository.manager.ICommandManagerRepository;
import com.ecommerce.manager.core.ports.out.repository.manager.IQueryManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class CreateManager {

    @Autowired
    private ICommandManagerRepository commandManagerRepository;

    @Autowired
    private IQueryManagerRepository queryManagerRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Transactional(rollbackFor = Exception.class)
    public Manager createManager(Manager manager) throws ManagerAlreadyExistsException {
        manager.setId("m@" + manager.getId());
        if(queryManagerRepository.existsById(manager.getId())) throw ManagerAlreadyExistsException.builder().id(manager.getId()).build();
        manager.setPassword(new BCryptPasswordEncoder().encode(manager.getPassword()));
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", Roles.MANAGER);
        manager.setToken(jwtProvider.generateToken(new HashMap<>(), manager.getId(), claims).replace("Bearer", ""));
        return commandManagerRepository.save(manager);
    }
}
