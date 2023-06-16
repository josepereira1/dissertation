package com.ecommerce.cc.core.ports.out.repository.version;

import com.ecommerce.cc.core.business.resources.Version;
import java.util.Optional;

public interface IQueryVersionRepository {
    Optional<Version> findOneForRead(String groupId);
    Optional<Version> findOneForUpdate(String groupId);
}
