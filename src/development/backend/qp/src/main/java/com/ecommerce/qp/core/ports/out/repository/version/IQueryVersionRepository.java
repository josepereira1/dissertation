package com.ecommerce.qp.core.ports.out.repository.version;

import com.ecommerce.qp.core.business.resources.Version;
import java.util.Optional;

public interface IQueryVersionRepository {
    Optional<Version> findById(String groupId);
}
