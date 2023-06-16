package com.ecommerce.cp.inbound.rest.version.command;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.resources.Version;
import com.ecommerce.cp.core.ports.in.version.ICommandVersion;
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
