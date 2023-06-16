package com.ecommerce.manager.core.business.logic.manager.command;

import com.ecommerce.manager.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.manager.core.business.logic.manager.command.features.CreateManager;
import com.ecommerce.manager.core.business.logic.manager.command.features.UpdateManager;
import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.core.ports.in.manager.ICommandManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandManager implements ICommandManager {

    @Autowired
    private CreateManager createManager;

    @Autowired
    private UpdateManager updateManager;

    @Override
    public Manager createManager(Manager manager) throws EcommerceBusinessLogicException {
        return createManager.createManager(manager);
    }

    @Override
    public Manager updateManager(String id, Manager manager) throws EcommerceBusinessLogicException{
        return updateManager.updateManager(id, manager);
    }

    @Override
    public void deleteManager(String id) throws EcommerceBusinessLogicException{

    }
}
