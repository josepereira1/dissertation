package com.ecommerce.manager.core.business.logic.manager.query;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.manager.core.business.logic.manager.query.features.ReadManager;
import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.core.ports.in.manager.IQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryManager implements IQueryManager {

    @Autowired
    private ReadManager readManager;

    @Override
    public Manager readManager(String id) throws EcommerceBusinessLogicException {
        return readManager.readManager(id);
    }
}
