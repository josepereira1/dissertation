package com.ecommerce.qcap.core.ports.out.repository.version;

import com.ecommerce.qcap.core.business.resources.Version;
import java.util.Optional;

public interface IQueryVersionRepository {
    Optional<Version> findById(String groupId);
}
