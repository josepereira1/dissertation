package com.ecommerce.cc.core.business.logic.version.command.features;

import com.ecommerce.cc.core.business.exceptions.version.VersionAlreadyExistsException;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.ports.out.repository.version.ICommandVersionRepository;
import com.ecommerce.cc.core.ports.out.repository.version.IQueryVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreateVersion {

    @Autowired
    private ICommandVersionRepository commandVersionRepository;

    @Autowired
    private IQueryVersionRepository queryVersionRepository;

    @Transactional
    public Version createVersion(Version version) throws VersionAlreadyExistsException {
        Optional<Version> optional = queryVersionRepository.findOneForUpdate(version.getGroupId());
        if(optional.isPresent()) throw VersionAlreadyExistsException.builder().groupId(version.getGroupId()).build();
        return commandVersionRepository.save(version);
    }
}
