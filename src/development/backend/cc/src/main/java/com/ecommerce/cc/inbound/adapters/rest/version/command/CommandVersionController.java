package com.ecommerce.cc.inbound.adapters.rest.version.command;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.ports.in.version.ICommandVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandVersionController implements ICommandVersionController{

    @Autowired
    private ICommandVersion commandVersion;

    @Override
    public ResponseEntity createVersion(@RequestBody Version version) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(commandVersion.createVersion(version));
    }
}
