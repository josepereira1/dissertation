package com.ecommerce.qp.core.business.logic.version.query;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qp.core.business.logic.version.query.features.ReadVersion;
import com.ecommerce.qp.core.business.resources.Version;
import com.ecommerce.qp.core.ports.in.version.IQueryVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryVersion implements IQueryVersion {

    @Autowired
    private ReadVersion readVersion;

    @Override
    public Version readVersion(String id) throws EcommerceBusinessLogicException {
        return readVersion.readVersion(id);
    }
}
