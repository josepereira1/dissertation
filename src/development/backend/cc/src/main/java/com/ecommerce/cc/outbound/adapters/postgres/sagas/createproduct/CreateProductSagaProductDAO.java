package com.ecommerce.cc.outbound.adapters.postgres.sagas.createproduct;

import com.ecommerce.cc.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CreateProductSagaProductDAO extends CrudRepository<CreateProductSaga, Long> {
    Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId);
}
