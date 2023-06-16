package com.ecommerce.qct.inbound.rest.version.command;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qct.core.business.resources.Version;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/versions/qct")
public interface ICommandVersionController {

    @PostMapping
    ResponseEntity createVersion(Version version) throws EcommerceBusinessLogicException;
}
