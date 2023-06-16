package com.ecommerce.co.core.business.security;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Authentication {

    @Autowired
    private JwtProvider jwtProvider;

    public Claims authenticateAndGetClaims(String token) {
        Claims claims;
        if ((token = jwtProvider.tokenIsValid(token)) != null && (claims = jwtProvider.validateTokenAndGetClaims(token)) != null) return claims;
        else return null;
    }
}
