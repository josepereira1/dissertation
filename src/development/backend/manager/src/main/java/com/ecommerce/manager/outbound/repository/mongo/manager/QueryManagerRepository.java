package com.ecommerce.manager.outbound.repository.mongo.manager;

import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.core.ports.out.repository.manager.IQueryManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryManagerRepository implements IQueryManagerRepository {

    @Autowired
    private ManagerDAO managerDAO;

    @Override
    public Optional<Manager> findById(String id) {
        return managerDAO.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return managerDAO.existsById(id);
    }
}
