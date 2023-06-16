package com.ecommerce.cc.inbound.adapters.rest.service;

import com.ecommerce.cc.inbound.adapters.rest.Response;
import com.ecommerce.cc.inbound.adapters.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping(path = "/api")
public class QueryServiceController {

        @Value("${service}")
        private String service;

        @Value("${version}")
        private String version;

        @GetMapping(path = "")
        public ResponseEntity status(){
                return ResponseEntity.ok(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.SUCCESS).appCode(Response.SUCCESS).message("Service is running version: " + version + ".").build());
        }
    }
