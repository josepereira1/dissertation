package com.ecommerce.manager.core.ports.out.repository.manager;

import com.ecommerce.manager.core.business.resources.Manager;

public interface ICommandManagerRepository {
    Manager save(Manager manager);
}
