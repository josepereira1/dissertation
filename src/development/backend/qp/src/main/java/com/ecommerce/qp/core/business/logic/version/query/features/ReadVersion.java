package com.ecommerce.qp.core.business.logic.version.query.features;

import com.ecommerce.qp.core.business.exceptions.version.VersionNotExistsException;
import com.ecommerce.qp.core.business.resources.Version;
import com.ecommerce.qp.core.ports.out.repository.version.IQueryVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadVersion {

    @Autowired
    private IQueryVersionRepository queryVersionRepository;

    public Version readVersion(String groupId) throws VersionNotExistsException {
        Optional<Version> optional = queryVersionRepository.findById(groupId);
        if(!optional.isPresent()) throw VersionNotExistsException.builder().groupId(groupId).build();
        return optional.get();
    }
}
