package com.ecommerce.cp.outbound.postgresql.product;

import com.ecommerce.cp.core.business.resources.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface ProductDAO extends JpaRepository<Product, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p.id from Product p where p.id = ?1 or p.sku = ?2 or p.ean = ?3")
    Optional<Product> existsProductByIdOrSkuOrEan(String id, String sku, String ean);

    @Lock(LockModeType.NONE)
    @Query("select p from Product p where p.id = ?1")
    Optional<Product> findOneForRead(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = ?1")
    Optional<Product> findOneForUpdate(String id);
}
