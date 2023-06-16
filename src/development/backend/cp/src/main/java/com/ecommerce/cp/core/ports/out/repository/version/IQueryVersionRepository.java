package com.ecommerce.cp.core.ports.out.repository.version;

import com.ecommerce.cp.core.business.resources.Version;
import java.util.Optional;

public interface IQueryVersionRepository {
    Optional<Version> findOneForRead(String groupId);
    Optional<Version> findOneForUpdate(String groupId);
}
