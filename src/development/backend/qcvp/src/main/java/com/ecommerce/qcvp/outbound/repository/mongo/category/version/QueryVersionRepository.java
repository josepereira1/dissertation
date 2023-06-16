package com.ecommerce.qcvp.outbound.repository.mongo.category.version;

import com.ecommerce.qcvp.core.business.resources.Version;
import com.ecommerce.qcvp.core.ports.out.repository.version.IQueryVersionRepository;
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
