package com.ecommerce.cc.outbound.adapters.postgres.sagas.updateproduct;

import com.ecommerce.cc.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface UpdateProductSagaProductDAO extends CrudRepository<UpdateProductSaga, Long> {
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select up from UpdateProductSaga up where up.sagaId = ?1")
    Optional<UpdateProductSaga> findOneForUpdate(String sagaId);
}
