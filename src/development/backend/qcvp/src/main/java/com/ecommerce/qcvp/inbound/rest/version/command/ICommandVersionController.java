package com.ecommerce.qcvp.inbound.rest.version.command;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcvp.core.business.resources.Version;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/versions/qc")
public interface ICommandVersionController {

    @PostMapping
    ResponseEntity createVersion(Version version) throws EcommerceBusinessLogicException;
}
