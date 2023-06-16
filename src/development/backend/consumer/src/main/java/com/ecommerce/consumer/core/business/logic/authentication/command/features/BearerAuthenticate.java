package com.ecommerce.consumer.core.business.logic.authentication.command.features;

import com.ecommerce.consumer.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.consumer.core.business.logic.authentication.command.util.ISecurity;
import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.core.business.resources.Role;
import com.ecommerce.consumer.core.ports.out.repository.consumer.ICommandConsumerRepository;
import com.ecommerce.consumer.core.ports.out.repository.consumer.IQueryConsumerRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BearerAuthenticate {

    private static final long SECOND = 1000;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;

    private static final long MAX_WINDOW = DAY;

    @Autowired
    private ISecurity authentication;

    @Autowired
    private ICommandConsumerRepository commandClientRepository;

    @Autowired
    private IQueryConsumerRepository queryClientRepository;

    @Transactional(rollbackFor = Exception.class)
    public Consumer authenticate(String token) throws UnauthorizedException {
        Claims claims = authentication.authenticate(token);
        long expirationTime = claims.getExpiration().getTime();
        long currentTime = System.currentTimeMillis();
        Optional<Consumer> optional = queryClientRepository.findById(claims.getSubject());
        if(optional.isPresent() && String.valueOf(claims.get("role")).toUpperCase().equals(Role.CONSUMER.name())){
            Consumer consumer = optional.get();
            if(expirationTime - currentTime <= MAX_WINDOW){
                Map<String, Object> newClaims = new HashMap<>();
                newClaims.put("role", Role.CONSUMER);
                //newClaims.put("email", client.getEmail());
                consumer.setToken(authentication.generateToken(new HashMap<>(), claims.getSubject(), newClaims).replace("Bearer", ""));
                commandClientRepository.save(consumer);
            }
            return consumer;
        } else throw UnauthorizedException.builder().build();
    }
}
