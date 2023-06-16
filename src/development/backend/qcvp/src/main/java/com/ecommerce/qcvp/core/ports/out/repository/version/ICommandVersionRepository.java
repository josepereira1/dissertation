package com.ecommerce.qcvp.core.ports.out.repository.version;

import com.ecommerce.qcvp.core.business.resources.Version;

public interface ICommandVersionRepository {
    Version save(Version version);
}
