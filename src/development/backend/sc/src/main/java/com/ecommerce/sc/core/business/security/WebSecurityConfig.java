package com.ecommerce.sc.core.business.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtProvider jwtProvider;

    private static final String[] SWAGGER_WHITE_LIST = {
            // -- Swagger UI v2
            "/",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/configuration/**",
            "/webjars/**",
            "/public",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api-docs",
            "/api-docs/swagger-config"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/*").permitAll();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return WebSecurityConfig::sendErrorMessage;
    }

    public static void sendErrorMessage(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        log.error(e.getMessage());
        e.printStackTrace();
        int status = HttpServletResponse.SC_FORBIDDEN;
        JSONObject jo = new JSONObject();
        try {
            jo.put("appCode", "");
            jo.put("message", "security error");
        } catch (Exception e1) {
            log.error("Could not create json error response message");
            return;
        }
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter pw = response.getWriter();
        pw.print(jo.toString());
        pw.flush();
    }
}
