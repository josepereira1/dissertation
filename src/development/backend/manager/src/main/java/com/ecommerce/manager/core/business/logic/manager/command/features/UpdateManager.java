package com.ecommerce.manager.core.business.logic.manager.command.features;

import com.ecommerce.manager.core.business.exceptions.manager.ManagerNotExistsException;
import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.core.ports.out.repository.manager.ICommandManagerRepository;
import com.ecommerce.manager.core.ports.out.repository.manager.IQueryManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateManager {

    @Autowired
    private CommonCommandManager commonCommandManager;

    @Autowired
    private ICommandManagerRepository commandEmployeeRepository;

    @Autowired
    private IQueryManagerRepository queryEmployeeRepository;

    @Transactional(rollbackFor = Exception.class)
    public Manager updateManager(String id, Manager manager) throws ManagerNotExistsException {
        Optional<Manager> optional = queryEmployeeRepository.findById(id);
        if(!optional.isPresent()) throw ManagerNotExistsException.builder().id(id).build();
        Manager currentManager = optional.get();
        currentManager = update(manager, currentManager);
        return commandEmployeeRepository.save(currentManager);
    }

    private Manager update(Manager updatedManager, Manager currentManager){
        if(updatedManager.getPassword() != null) currentManager.setPassword(commonCommandManager.passwordEncoder().encode(updatedManager.getPassword()));
        if(updatedManager.getEmail() != null) currentManager.setEmail(updatedManager.getEmail());
        return currentManager;
    }
}
