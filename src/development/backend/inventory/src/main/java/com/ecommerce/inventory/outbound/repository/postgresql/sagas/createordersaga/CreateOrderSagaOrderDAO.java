package com.ecommerce.inventory.outbound.repository.postgresql.sagas.createordersaga;

import com.ecommerce.inventory.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CreateOrderSagaOrderDAO extends JpaRepository<CreateOrderSaga, Long> {
    Optional<CreateOrderSaga> findCreateOrderSagaBySagaId(String sagaId);
}
