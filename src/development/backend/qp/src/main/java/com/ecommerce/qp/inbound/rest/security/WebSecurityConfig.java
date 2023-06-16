package com.ecommerce.qp.inbound.rest.security;

import com.ecommerce.qp.inbound.rest.Response;
import com.ecommerce.qp.inbound.rest.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
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
import java.time.LocalDateTime;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/*").permitAll();

        // custom 'forbidden' json message
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return WebSecurityConfig::sendErrorMessage;
    }

    public static void sendErrorMessage(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        e.printStackTrace();
        log.error(e.getMessage());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter pw = response.getWriter();
        pw.print(Response.builder().service("qp").timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.AUTHENTICATION_FAIL).message(e.getMessage()).build());
        pw.flush();
    }
}
