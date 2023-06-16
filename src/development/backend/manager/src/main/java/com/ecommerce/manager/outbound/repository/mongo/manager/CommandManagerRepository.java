package com.ecommerce.manager.outbound.repository.mongo.manager;

import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.core.ports.out.repository.manager.ICommandManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandManagerRepository implements ICommandManagerRepository {

    @Autowired
    private ManagerDAO managerDAO;

    @Override
    public Manager save(Manager manager) {
        return managerDAO.save(manager);
    }
}
