package com.ecommerce.qct.core.ports.in.version;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qct.core.business.resources.Version;

public interface IQueryVersion {
    Version readVersion(String id) throws EcommerceBusinessLogicException;
}
