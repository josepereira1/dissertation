package com.ecommerce.inventory.outbound.repository.postgresql.sagas.createproductsaga;

import com.ecommerce.inventory.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CreateProductSagaProductDAO extends JpaRepository<CreateProductSaga, Long> {
    Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId);
}
