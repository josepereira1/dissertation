package com.ecommerce.qp.core.ports.out.repository.version;

import com.ecommerce.qp.core.business.resources.Version;

public interface ICommandVersionRepository {
    Version save(Version version);
}
