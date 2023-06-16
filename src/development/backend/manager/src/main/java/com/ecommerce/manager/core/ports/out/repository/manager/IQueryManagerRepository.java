package com.ecommerce.manager.core.ports.out.repository.manager;

import com.ecommerce.manager.core.business.resources.Manager;

import java.util.Optional;

public interface IQueryManagerRepository {
    Optional<Manager> findById(String id);
    boolean existsById(String id);
}
