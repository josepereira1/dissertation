package com.ecommerce.qcvp.core.ports.in.version;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcvp.core.business.resources.Version;

public interface IQueryVersion {
    Version readVersion(String id) throws EcommerceBusinessLogicException;
}
