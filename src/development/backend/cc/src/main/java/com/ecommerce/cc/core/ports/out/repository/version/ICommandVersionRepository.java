package com.ecommerce.cc.core.ports.out.repository.version;

import com.ecommerce.cc.core.business.resources.Version;

public interface ICommandVersionRepository {
    Version save(Version version);
    void deleteById(String groupId);
}
