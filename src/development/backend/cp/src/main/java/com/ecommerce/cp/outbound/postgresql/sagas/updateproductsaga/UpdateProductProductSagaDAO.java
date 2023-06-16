package com.ecommerce.cp.outbound.postgresql.sagas.updateproductsaga;

import com.ecommerce.cp.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UpdateProductProductSagaDAO extends JpaRepository<UpdateProductSaga, String> {
    Optional<UpdateProductSaga> findCreateProductSagaBySagaId(String sagaId);
}
