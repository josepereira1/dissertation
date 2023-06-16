package com.ecommerce.qct.core.ports.out.repository.version;

import com.ecommerce.qct.core.business.resources.Version;
import java.util.Optional;

public interface IQueryVersionRepository {
    Optional<Version> findById(String groupId);
}
