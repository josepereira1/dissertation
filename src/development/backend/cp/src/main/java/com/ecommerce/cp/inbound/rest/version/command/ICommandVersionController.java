package com.ecommerce.cp.inbound.rest.version.command;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.resources.Version;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/versions")
public interface ICommandVersionController {

    @PostMapping
    ResponseEntity createVersion(Version version) throws EcommerceBusinessLogicException;
}
