package com.ecommerce.cp.outbound.postgresql.sagas.createproductsaga;

import com.ecommerce.cp.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CreateProductProductSagaDAO extends JpaRepository<CreateProductSaga, String> {
    Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId);
}
