package com.ecommerce.qp.core.ports.in.version;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qp.core.business.resources.Version;

public interface IQueryVersion {
    Version readVersion(String id) throws EcommerceBusinessLogicException;
}
