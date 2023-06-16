package com.ecommerce.manager.core.business.logic.auth.command.features;

import com.ecommerce.manager.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.manager.core.business.logic.auth.command.util.security.ISecurity;
import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.core.business.resources.Roles;
import com.ecommerce.manager.core.ports.out.repository.manager.ICommandManagerRepository;
import com.ecommerce.manager.core.ports.out.repository.manager.IQueryManagerRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BearerAuthentication {

    private static final long SECOND = 1000;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;

    private static final long MAX_WINDOW = DAY;

    @Autowired
    private ISecurity authentication;

    @Autowired
    private ICommandManagerRepository commandManagerRepository;

    @Autowired
    private IQueryManagerRepository queryManagerRepository;

    @Transactional(rollbackFor = Exception.class)
    public Manager authenticate(String token) throws UnauthorizedException {
        Claims claims = authentication.authenticate(token);
        long expirationTime = claims.getExpiration().getTime();
        long currentTime = System.currentTimeMillis();
        Optional<Manager> optional = queryManagerRepository.findById(claims.getSubject());
        if(optional.isPresent() && String.valueOf(claims.get("role")).toUpperCase().equals(Roles.MANAGER.name())){
            Manager manager = optional.get();
            if(expirationTime - currentTime <= MAX_WINDOW){
                Map<String, Object> newClaims = new HashMap<>();
                newClaims.put("role", Roles.MANAGER);
                newClaims.put("email", manager.getEmail());
                manager.setToken(authentication.generateToken(new HashMap<>(), claims.getSubject(), newClaims).replace("Bearer", ""));
                commandManagerRepository.save(manager);
            }
            return manager;
        } else throw UnauthorizedException.builder().build();
    }
}
