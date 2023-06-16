package com.example.ecommerce.saga.inbound.rest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Slf4j
@Component
public class JwtProvider {

    @Value("${security.jwt.prefix}")
    private String prefix;

    @Value("${security.jwt.secret}")
    private String secret;

    @PostConstruct
    public void init() {
    }

    public String tokenIsValid(String token) {
        if (token != null && token.startsWith(prefix)) return token.replace(prefix, "");
        else return null;
    }

    public Claims validateTokenAndGetClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
