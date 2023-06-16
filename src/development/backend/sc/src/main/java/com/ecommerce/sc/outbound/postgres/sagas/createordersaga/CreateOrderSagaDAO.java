package com.ecommerce.sc.outbound.postgres.sagas.createordersaga;

import com.ecommerce.sc.core.business.sagas.createorder.resources.CreateOrderSaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CreateOrderSagaDAO extends JpaRepository<CreateOrderSaga, Long> {
    Optional<CreateOrderSaga> findCreateOrderSagaBySagaId(String sagaId);
}
