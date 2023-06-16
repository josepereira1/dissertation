package com.ecommerce.qcap.outbound.repository.mongo.category.version;

import com.ecommerce.qcap.core.business.resources.Version;
import com.ecommerce.qcap.core.ports.out.repository.version.ICommandVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandVersionRepository implements ICommandVersionRepository {

    @Autowired
    private VersionDAO versionDAO;

    @Override
    public Version save(Version version) {
        return versionDAO.save(version);
    }
}
