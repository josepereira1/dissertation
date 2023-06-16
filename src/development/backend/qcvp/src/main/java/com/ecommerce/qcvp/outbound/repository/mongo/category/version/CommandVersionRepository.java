package com.ecommerce.qcvp.outbound.repository.mongo.category.version;

import com.ecommerce.qcvp.core.business.resources.Version;
import com.ecommerce.qcvp.core.ports.out.repository.version.ICommandVersionRepository;
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
