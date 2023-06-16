package com.ecommerce.qcvp.outbound.repository.mongo.category.version;

import com.ecommerce.qcvp.core.business.resources.Version;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionDAO extends MongoRepository<Version, String> {
}
