package com.ecommerce.cp.core.ports.out.repository.version;


import com.ecommerce.cp.core.business.resources.Version;

public interface ICommandVersionRepository {
    Version save(Version version);
    void deleteById(String groupId);
}
