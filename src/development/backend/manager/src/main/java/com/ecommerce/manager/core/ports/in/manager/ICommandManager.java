package com.ecommerce.manager.core.ports.in.manager;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.manager.core.business.resources.Manager;

public interface ICommandManager {
    Manager createManager(Manager manager) throws EcommerceBusinessLogicException;
    Manager updateManager(String id, Manager manager) throws EcommerceBusinessLogicException;
    void deleteManager(String id) throws EcommerceBusinessLogicException;
}
