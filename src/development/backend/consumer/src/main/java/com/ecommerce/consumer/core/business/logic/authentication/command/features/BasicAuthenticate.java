package com.ecommerce.consumer.core.business.logic.authentication.command.features;

import com.ecommerce.consumer.core.business.exceptions.authentication.AuthenticationFailException;
import com.ecommerce.consumer.core.business.logic.authentication.command.util.JwtProvider;
import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.core.business.resources.Role;
import com.ecommerce.consumer.core.ports.out.repository.consumer.ICommandConsumerRepository;
import com.ecommerce.consumer.core.ports.out.repository.consumer.IQueryConsumerRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("AuthenticationBusiness")
public class BasicAuthenticate {

    @Autowired
    private ICommandConsumerRepository commandEmployeeRepository;

    @Autowired
    private IQueryConsumerRepository queryEmployeeRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Transactional(rollbackFor = Exception.class)
    public Consumer authentication(String id, String password) throws AuthenticationFailException {
        Optional<Consumer> optional = queryEmployeeRepository.findById(id);
        if(optional.isPresent() && optional.get() != null && new BCryptPasswordEncoder().matches(password, optional.get().getPassword())){
            Consumer consumer = optional.get();
            //  TODO if exp date is close, should generate new token
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", Role.CONSUMER);
            claims.put("email", consumer.getContacts().getEmail());
            try {
                if (consumer.getToken() == null || jwtProvider.validateTokenAndGetClaims(consumer.getToken()) == null)
                    consumer.setToken(jwtProvider.generateToken(new HashMap<>(), id, claims).replace("Bearer", ""));
            } catch (ExpiredJwtException e){
                e.printStackTrace();
                consumer.setToken(jwtProvider.generateToken(new HashMap<>(), id, claims).replace("Bearer", ""));
            }
            commandEmployeeRepository.save(consumer);
            return consumer;
        } else throw AuthenticationFailException.builder().id(id).build();
    }
}
