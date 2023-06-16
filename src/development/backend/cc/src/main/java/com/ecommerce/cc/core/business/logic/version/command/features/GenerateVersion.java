package com.ecommerce.cc.core.business.logic.version.command.features;

import com.ecommerce.cc.core.business.exceptions.version.VersionNotExistsException;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.ports.out.repository.version.ICommandVersionRepository;
import com.ecommerce.cc.core.ports.out.repository.version.IQueryVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GenerateVersion {

    @Autowired
    private ICommandVersionRepository commandVersionRepository;

    @Autowired
    private IQueryVersionRepository queryVersionRepository;

    @Transactional(rollbackFor = Exception.class)
    public Version generateVersion(String groupId) throws VersionNotExistsException {
        Optional<Version> optional = queryVersionRepository.findOneForUpdate(groupId);
        if(!optional.isPresent()) {
            throw VersionNotExistsException.builder().groupId(groupId).build();
        }
        Version currentVersion = optional.get(), response = new Version();
        response.setGroupId(currentVersion.getGroupId());
        response.setValue(currentVersion.getValue());
        currentVersion.setValue(currentVersion.getValue() + 1);
        commandVersionRepository.save(currentVersion);
        return response;
    }
}
