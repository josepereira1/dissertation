package com.ecommerce.qcvp.core.ports.out.repository.version;

import com.ecommerce.qcvp.core.business.resources.Version;
import java.util.Optional;

public interface IQueryVersionRepository {
    Optional<Version> findById(String groupId);
}
