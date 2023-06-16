package com.ecommerce.qp.outbound.repository.mongo.version;

import com.ecommerce.qp.core.business.resources.Version;
import com.ecommerce.qp.core.ports.out.repository.version.IQueryVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryVersionRepository implements IQueryVersionRepository {

    @Autowired
    private VersionDAO versionDAO;

    @Override
    public Optional<Version> findById(String groupId) {
        return versionDAO.findById(groupId);
    }
}
