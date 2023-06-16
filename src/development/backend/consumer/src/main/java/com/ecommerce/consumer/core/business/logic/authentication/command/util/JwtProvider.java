package com.ecommerce.consumer.core.business.logic.authentication.command.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Component
public class JwtProvider implements ISecurity {
    @Value("${security.jwt.prefix}")
    private String prefix;
    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${security.jwt.key}")
    private String key;
    @Value("${security.jwt.expiration-time}")
    private long expirationTime;
    @Value("${security.jwt.signature-algorithm}")
    private String signatureAlgorithmName;
    private SignatureAlgorithm signatureAlgorithm;

    @PostConstruct
    public void init() {
        this.signatureAlgorithm = SignatureAlgorithm.forName(signatureAlgorithmName);
    }

    public String tokenIsValid(String token) {
        if (token != null && token.startsWith(prefix)) return token.replace(prefix, "");
        else return null;
    }

    public Claims validateTokenAndGetClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateToken(Map<String, Object> headers, String subject,  Map<String, Object> claims) {
        claims.put("iss", key);
        String token = Jwts
                .builder()
                .setHeader(headers)
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (expirationTime)))
                .signWith(signatureAlgorithm, secret)
                .compact();
        return prefix + token;
    }

    @Override
    public Claims authenticate(String token) throws ExpiredJwtException {
        Claims claims;
        if ((token = tokenIsValid(token)) != null && (claims = validateTokenAndGetClaims(token)) != null)
            return claims;
        else
            return null;
    }
}
