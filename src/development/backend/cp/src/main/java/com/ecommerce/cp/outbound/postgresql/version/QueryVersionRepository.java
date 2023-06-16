package com.ecommerce.cp.outbound.postgresql.version;

import com.ecommerce.cp.core.business.resources.Version;
import com.ecommerce.cp.core.ports.out.repository.version.IQueryVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryVersionRepository implements IQueryVersionRepository {

    @Autowired
    private VersionDAO versionDAO;

    @Override
    public Optional<Version> findOneForRead(String groupId) {
        return versionDAO.findOneForRead(groupId);
    }

    @Override
    public Optional<Version> findOneForUpdate(String groupId) {
        return versionDAO.findOneForUpdate(groupId);
    }
}
