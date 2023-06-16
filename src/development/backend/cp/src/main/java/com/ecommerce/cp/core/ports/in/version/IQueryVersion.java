package com.ecommerce.cp.core.ports.in.version;

import com.ecommerce.cp.core.business.resources.Version;

public interface IQueryVersion {
    Version readVersion(String groupId);
}
