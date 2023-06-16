package com.ecommerce.sc.outbound.postgres.shoppingcart;

import com.ecommerce.sc.core.business.resources.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface ShoppingCartDAO extends JpaRepository<ShoppingCart, String> {

    @Lock(LockModeType.NONE)
    @Query("select sc from ShoppingCart sc where sc.id = ?1")
    Optional<ShoppingCart> findOneForRead(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select sc from ShoppingCart sc where sc.id = ?1")
    Optional<ShoppingCart> findOneForUpdate(String id);

    @Lock(LockModeType.NONE)
    @Query("select sc.size from ShoppingCart sc where sc.id = ?1")
    Optional<Integer> findShoppingCartSize(String id);
}
