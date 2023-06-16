package com.ecommerce.qp.outbound.repository.mongo.version;

import com.ecommerce.qp.core.business.resources.Version;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionDAO extends MongoRepository<Version, String> {
}
