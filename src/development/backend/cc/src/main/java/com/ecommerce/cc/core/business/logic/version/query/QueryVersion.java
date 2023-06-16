package com.ecommerce.cc.core.business.logic.version.query;

import com.ecommerce.cc.core.business.logic.version.query.features.ReadVersion;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.ports.in.version.IQueryVersion;
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
