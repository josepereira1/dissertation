package com.ecommerce.sc.outbound.postgres.sagas.createproductsaga;

import com.ecommerce.sc.core.business.sagas.createproduct.resources.CreateProductSaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CreateProductSagaDAO extends JpaRepository<CreateProductSaga, Long> {
    Optional<CreateProductSaga> findCreateProductSagaBySagaId(String sagaId);
}
