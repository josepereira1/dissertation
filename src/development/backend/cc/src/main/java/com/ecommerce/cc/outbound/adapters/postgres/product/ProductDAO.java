package com.ecommerce.cc.outbound.adapters.postgres.product;

import com.ecommerce.cc.core.business.resources.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface ProductDAO extends JpaRepository<Product, String> {

    @Lock(LockModeType.NONE)
    @Query("select p from Product p where p.id = ?1")
    Optional<Product> findOneForRead(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = ?1")
    Optional<Product> findOneForUpdate(String id);
}
