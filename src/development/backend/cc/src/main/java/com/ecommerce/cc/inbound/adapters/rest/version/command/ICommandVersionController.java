package com.ecommerce.cc.inbound.adapters.rest.version.command;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.resources.Version;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/versions/cc")
public interface ICommandVersionController {

    @PostMapping
    ResponseEntity createVersion(Version version) throws EcommerceBusinessLogicException;
}
