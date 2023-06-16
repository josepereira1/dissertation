package com.ecommerce.cc.core.ports.in.version;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.resources.Version;

public interface ICommandVersion {
    Version createVersion(Version version) throws EcommerceBusinessLogicException;
    Version updateVersion(String groupId, Version version) throws EcommerceBusinessLogicException;
    void deleteVersion(String groupId) throws EcommerceBusinessLogicException;
    Version generateVersion(String groupId) throws EcommerceBusinessLogicException;
}
