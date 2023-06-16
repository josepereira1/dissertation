package com.ecommerce.qcap.core.ports.out.repository.version;

import com.ecommerce.qcap.core.business.resources.Version;

public interface ICommandVersionRepository {
    Version save(Version version);
}
