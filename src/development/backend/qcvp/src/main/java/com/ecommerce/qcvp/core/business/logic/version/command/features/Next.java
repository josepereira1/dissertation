package com.ecommerce.qcvp.core.business.logic.version.command.features;

import com.ecommerce.qcvp.core.business.exceptions.version.VersionNotExistsException;
import com.ecommerce.qcvp.core.business.resources.Version;
import com.ecommerce.qcvp.core.ports.out.repository.version.ICommandVersionRepository;
import com.ecommerce.qcvp.core.ports.out.repository.version.IQueryVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class Next {

    @Autowired
    private ICommandVersionRepository commandVersionRepository;

    @Autowired
    private IQueryVersionRepository queryVersionRepository;

    @Transactional(rollbackFor = Exception.class)
    public Version next(Version inputVersion) throws VersionNotExistsException {
        Optional<Version> optional = queryVersionRepository.findById(inputVersion.getGroupId());
        if(!optional.isPresent()) throw VersionNotExistsException.builder().groupId(inputVersion.getGroupId()).build();
        if(optional.get().getValue().equals(inputVersion.getValue())){
            Version version = optional.get();
            version.setValue(version.getValue() + 1);
            return commandVersionRepository.save(version);
        }else return null;
    }
}
