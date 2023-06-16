package com.ecommerce.sc.outbound.postgres.sagas.updateproduct;

import com.ecommerce.sc.core.business.sagas.updateproduct.resources.UpdateProductSaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UpdateProductSagaDAO extends JpaRepository<UpdateProductSaga, Long> {
    Optional<UpdateProductSaga> findUpdateProductSagaBySagaId(String sagaId);
}
