package com.ecommerce.manager.outbound.repository.mongo.manager;

import com.ecommerce.manager.core.business.resources.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDAO extends MongoRepository<Manager, String> {
}
