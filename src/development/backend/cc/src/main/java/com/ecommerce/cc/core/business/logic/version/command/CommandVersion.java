package com.ecommerce.cc.core.business.logic.version.command;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.logic.version.command.features.CreateVersion;
import com.ecommerce.cc.core.business.logic.version.command.features.DeleteVersion;
import com.ecommerce.cc.core.business.logic.version.command.features.GenerateVersion;
import com.ecommerce.cc.core.business.logic.version.command.features.UpdateVersion;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.ports.in.version.ICommandVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandVersion implements ICommandVersion {

    @Autowired
    private CreateVersion createVersion;

    @Autowired
    private UpdateVersion updateVersion;

    @Autowired
    private DeleteVersion deleteVersion;

    @Autowired
    private GenerateVersion generateVersion;

    @Override
    public Version createVersion(Version version) throws EcommerceBusinessLogicException{
        return createVersion.createVersion(version);
    }

    @Override
    public Version updateVersion(String groupId, Version version) throws EcommerceBusinessLogicException {
        return updateVersion.updateVersion(groupId, version);
    }

    @Override
    public void deleteVersion(String groupId) throws EcommerceBusinessLogicException{
        deleteVersion.deleteVersion(groupId);
    }

    @Override
    public Version generateVersion(String groupId) throws EcommerceBusinessLogicException{
        return generateVersion.generateVersion(groupId);
    }
}
