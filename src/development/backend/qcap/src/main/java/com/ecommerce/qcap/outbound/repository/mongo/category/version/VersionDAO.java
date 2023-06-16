package com.ecommerce.qcap.outbound.repository.mongo.category.version;

import com.ecommerce.qcap.core.business.resources.Version;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionDAO extends MongoRepository<Version, String> {
}
