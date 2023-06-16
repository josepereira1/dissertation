package com.ecommerce.qct.core.business.logic.version.command.features;

import com.ecommerce.qct.core.business.exceptions.version.VersionNotExistsException;
import com.ecommerce.qct.core.business.resources.Version;
import com.ecommerce.qct.core.ports.out.repository.version.IQueryVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class IsNext {

    @Autowired
    private IQueryVersionRepository queryVersionRepository;

    public int isNext(Version inputVersion) {
        Optional<Version> optional = queryVersionRepository.findById(inputVersion.getGroupId());
        if(!optional.isPresent()) return 20;
        return inputVersion.getValue() - optional.get().getValue();
    }
}
