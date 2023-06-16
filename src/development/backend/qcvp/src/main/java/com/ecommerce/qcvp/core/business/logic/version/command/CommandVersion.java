package com.ecommerce.qcvp.core.business.logic.version.command;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcvp.core.business.logic.version.command.features.CreateVersion;
import com.ecommerce.qcvp.core.business.logic.version.command.features.IsNext;
import com.ecommerce.qcvp.core.business.logic.version.command.features.Next;
import com.ecommerce.qcvp.core.business.resources.Version;
import com.ecommerce.qcvp.core.ports.in.version.ICommandVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandVersion implements ICommandVersion {

    @Autowired
    private CreateVersion createVersion;

    @Autowired
    private IsNext isNext;

    @Autowired
    private Next next;

    @Override
    public Version createVersion(Version version) throws EcommerceBusinessLogicException {
        return createVersion.createVersion(version);
    }

    @Override
    public Version updateVersion(String groupId, Version version) {
        return null;
    }

    @Override
    public Version deleteVersion(String groupId) {
        return null;
    }

    @Override
    public int isNext(Version version) {
        return isNext.isNext(version);
    }

    @Override
    public Version next(Version version) throws EcommerceBusinessLogicException {
        return next.next(version);
    }
}
