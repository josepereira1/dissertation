package com.ecommerce.sc.outbound.postgres.product;

import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.business.resources.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface ProductDAO extends JpaRepository<Product, String> {

    @Lock(LockModeType.NONE)
    @Query("select p from Product p where p.id = ?1")
    Optional<ShoppingCart> findOneForRead(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = ?1")
    Optional<Product> findOneForUpdate(String id);
}
