package com.ecommerce.inventory.outbound.repository.postgresql.sagas.updateproductsaga;

import com.ecommerce.inventory.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UpdateProductSagaProductDAO extends JpaRepository<UpdateProductSaga, String> {
    Optional<UpdateProductSaga> findUpdateProductSagaBySagaId(String sagaId);
}
