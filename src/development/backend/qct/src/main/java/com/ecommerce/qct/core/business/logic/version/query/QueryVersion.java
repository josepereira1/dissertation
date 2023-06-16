package com.ecommerce.qct.core.business.logic.version.query;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qct.core.business.logic.version.query.features.ReadVersion;
import com.ecommerce.qct.core.business.resources.Version;
import com.ecommerce.qct.core.ports.in.version.IQueryVersion;
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
