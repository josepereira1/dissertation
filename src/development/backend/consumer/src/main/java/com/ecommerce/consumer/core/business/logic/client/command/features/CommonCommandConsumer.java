package com.ecommerce.consumer.core.business.logic.client.command.features;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CommonCommandConsumer {

    @Value("${security.password-encoder.strength}")
    private int passwordEncoderStrength;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(passwordEncoderStrength);
    }
}
