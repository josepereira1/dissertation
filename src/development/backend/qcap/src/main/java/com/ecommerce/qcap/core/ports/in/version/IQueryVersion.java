package com.ecommerce.qcap.core.ports.in.version;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcap.core.business.resources.Version;

public interface IQueryVersion {
    Version readVersion(String id) throws EcommerceBusinessLogicException;
}
