package com.ecommerce.cc.core.business.logic.version.query.features;

import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.ports.out.repository.version.IQueryVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadVersion {

    @Autowired
    private IQueryVersionRepository queryVersionRepository;

    public Version readVersion(String id){
        return queryVersionRepository.findOneForRead(id).get();
    }
}
