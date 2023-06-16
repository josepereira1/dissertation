package com.ecommerce.manager.core.business.logic.manager.query.features;

import com.ecommerce.manager.core.business.exceptions.manager.ManagerNotExistsException;
import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.core.ports.out.repository.manager.IQueryManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadManager {

    @Autowired
    private IQueryManagerRepository queryEmployeeRepository;

    public Manager readManager(String id) throws ManagerNotExistsException {
        Optional<Manager> optional = queryEmployeeRepository.findById(id);
        if(!optional.isPresent()) throw ManagerNotExistsException.builder().id(id).build();
        return optional.get();
    }
}
