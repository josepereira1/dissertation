package com.ecommerce.cp.core.ports.in.version;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.resources.Version;

public interface ICommandVersion {
    Version createVersion(Version version) throws EcommerceBusinessLogicException;
    Version updateVersion(String groupId, Version version) throws EcommerceBusinessLogicException;
    void deleteVersion(String groupId) throws EcommerceBusinessLogicException;
    Version generateVersion(String groupId) throws EcommerceBusinessLogicException;
}
