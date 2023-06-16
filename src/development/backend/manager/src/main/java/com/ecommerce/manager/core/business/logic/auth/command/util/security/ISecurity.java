package com.ecommerce.manager.core.business.logic.auth.command.util.security;

import io.jsonwebtoken.Claims;
import java.util.Map;

public interface ISecurity {
    Claims authenticate(String token);
    String generateToken(Map<String, Object> headers, String subject, Map<String, Object> claims);
}
