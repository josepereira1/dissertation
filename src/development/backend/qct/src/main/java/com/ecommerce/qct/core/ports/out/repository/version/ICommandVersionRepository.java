package com.ecommerce.qct.core.ports.out.repository.version;

import com.ecommerce.qct.core.business.resources.Version;

public interface ICommandVersionRepository {
    Version save(Version version);
}
