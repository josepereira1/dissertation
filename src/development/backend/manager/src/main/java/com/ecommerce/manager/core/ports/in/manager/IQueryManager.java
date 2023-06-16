package com.ecommerce.manager.core.ports.in.manager;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.manager.core.business.resources.Manager;

public interface IQueryManager {
    Manager readManager(String id) throws EcommerceBusinessLogicException;
}
