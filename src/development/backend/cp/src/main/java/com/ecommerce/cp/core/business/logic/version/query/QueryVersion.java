package com.ecommerce.cp.core.business.logic.version.query;

import com.ecommerce.cp.core.business.logic.version.query.features.ReadVersion;
import com.ecommerce.cp.core.business.resources.Version;
import com.ecommerce.cp.core.ports.in.version.IQueryVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryVersion implements IQueryVersion {

    @Autowired
    private ReadVersion readVersion;

    @Override
    public Version readVersion(String groupId){
        return readVersion.readVersion(groupId);
    }
}
