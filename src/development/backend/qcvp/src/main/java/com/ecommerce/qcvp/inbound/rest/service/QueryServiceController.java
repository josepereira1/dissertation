package com.ecommerce.qcvp.inbound.rest.service;

import com.ecommerce.qcvp.inbound.rest.Response;
import com.ecommerce.qcvp.inbound.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/api")
public class QueryServiceController {

        private final String VERSION = "0.0.1";

        @CrossOrigin
        @GetMapping(path = "")
        public ResponseEntity status(@Value("${service}") String service){
                return ResponseEntity.ok(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.SUCCESS).appCode(Response.SUCCESS).message("Service is running version: " + VERSION + ".").build());
        }
    }
