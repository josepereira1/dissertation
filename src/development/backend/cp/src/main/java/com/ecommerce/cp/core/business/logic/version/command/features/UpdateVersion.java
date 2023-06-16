package com.ecommerce.cp.core.business.logic.version.command.features;

import com.ecommerce.cp.core.business.exceptions.version.VersionNotExistsException;
import com.ecommerce.cp.core.business.resources.Version;
import com.ecommerce.cp.core.ports.out.repository.version.ICommandVersionRepository;
import com.ecommerce.cp.core.ports.out.repository.version.IQueryVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateVersion {

    @Autowired
    private ICommandVersionRepository commandVersionRepository;

    @Autowired
    private IQueryVersionRepository queryVersionRepository;

    @Transactional(rollbackFor = Exception.class)
    public Version updateVersion(String groupId, Version updatedVersion) throws VersionNotExistsException {
        Optional<Version> optional = queryVersionRepository.findOneForUpdate(groupId);
        if(!optional.isPresent()) throw VersionNotExistsException.builder().groupId(groupId).build();
        Version currentVersion = optional.get();
        if(updatedVersion.getDescription() != null) currentVersion.setDescription(updatedVersion.getDescription());
        if(updatedVersion.getValue() != null) currentVersion.setValue(updatedVersion.getValue());
        return commandVersionRepository.save(currentVersion);
    }
}
