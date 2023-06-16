package com.ecommerce.manager.core.business.logic.auth.command.features;

import com.ecommerce.manager.core.business.exceptions.authentication.AuthenticationFailException;
import com.ecommerce.manager.core.business.logic.auth.command.util.security.JwtProvider;
import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.core.business.resources.Roles;
import com.ecommerce.manager.core.ports.out.repository.manager.ICommandManagerRepository;
import com.ecommerce.manager.core.ports.out.repository.manager.IQueryManagerRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("AuthenticationBusiness")
public class BasicAuthentication {

    @Autowired
    private ICommandManagerRepository commandEmployeeRepository;

    @Autowired
    private IQueryManagerRepository queryEmployeeRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Transactional(rollbackFor = Exception.class)
    public Manager authenticate(String id, String password) throws AuthenticationFailException {
        Optional<Manager> optional = queryEmployeeRepository.findById(id);

        if(optional.isPresent() && optional.get() != null && new BCryptPasswordEncoder().matches(password, optional.get().getPassword())){
            Manager manager = optional.get();
            //  TODO if exp date is close, should generate new token
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", Roles.MANAGER);
            claims.put("email", manager.getEmail());
            try {
                if (manager.getToken() == null || jwtProvider.validateTokenAndGetClaims(manager.getToken()) == null) {
                    manager.setToken(jwtProvider.generateToken(new HashMap<>(), id, claims).replace("Bearer", ""));
                }
            }catch (ExpiredJwtException e){
                e.printStackTrace();
                manager.setToken(jwtProvider.generateToken(new HashMap<>(), id, claims).replace("Bearer", ""));
            }
            commandEmployeeRepository.save(manager);
            return manager;
        } else throw AuthenticationFailException.builder().id(id).build();
    }
}
