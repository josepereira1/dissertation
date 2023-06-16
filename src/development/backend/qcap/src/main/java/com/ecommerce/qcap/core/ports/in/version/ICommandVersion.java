package com.ecommerce.qcap.core.ports.in.version;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcap.core.business.resources.Version;

public interface ICommandVersion {
    Version createVersion(Version version) throws EcommerceBusinessLogicException;
    Version updateVersion(String groupId, Version version);
    Version deleteVersion(String groupId);
    int isNext(Version version) ;
    Version next(Version version) throws EcommerceBusinessLogicException;
}
