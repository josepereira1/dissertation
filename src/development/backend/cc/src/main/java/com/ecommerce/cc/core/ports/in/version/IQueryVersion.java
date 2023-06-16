package com.ecommerce.cc.core.ports.in.version;

import com.ecommerce.cc.core.business.resources.Version;

public interface IQueryVersion {
    Version readVersion(String groupId);
}
