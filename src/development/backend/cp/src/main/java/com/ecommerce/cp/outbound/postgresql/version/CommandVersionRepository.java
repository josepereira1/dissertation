package com.ecommerce.cp.outbound.postgresql.version;

import com.ecommerce.cp.core.business.resources.Version;
import com.ecommerce.cp.core.ports.out.repository.version.ICommandVersionRepository;
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

    @Override
    public void deleteById(String groupId) {
        versionDAO.deleteById(groupId);
    }
}
