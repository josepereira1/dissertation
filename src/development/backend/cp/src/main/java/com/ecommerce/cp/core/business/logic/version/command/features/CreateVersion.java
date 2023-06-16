package com.ecommerce.cp.core.business.logic.version.command.features;

import com.ecommerce.cp.core.business.exceptions.version.VersionAlreadyExistsException;
import com.ecommerce.cp.core.business.resources.Version;
import com.ecommerce.cp.core.ports.out.repository.version.ICommandVersionRepository;
import com.ecommerce.cp.core.ports.out.repository.version.IQueryVersionRepository;
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

    @Transactional(rollbackFor = Exception.class)
    public Version createVersion(Version version) throws VersionAlreadyExistsException {
        Optional<Version> optional = queryVersionRepository.findOneForUpdate(version.getGroupId());
        if(optional.isPresent()) throw VersionAlreadyExistsException.builder().groupId(version.getGroupId()).build();
        return commandVersionRepository.save(version);
    }
}
