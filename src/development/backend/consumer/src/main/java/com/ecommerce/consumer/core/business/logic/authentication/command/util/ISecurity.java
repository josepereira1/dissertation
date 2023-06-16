package com.ecommerce.consumer.core.business.logic.authentication.command.util;

import io.jsonwebtoken.Claims;
import java.util.Map;

public interface ISecurity {
    Claims authenticate(String token);
    String generateToken(Map<String, Object> headers, String subject, Map<String, Object> claims);
}
