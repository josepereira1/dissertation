package com.ecommerce.inventory.outbound.repository.postgresql.product;

import com.ecommerce.inventory.core.business.resources.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface ProductDAO extends JpaRepository<Product, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = ?1")
    Optional<Product> findOneForUpdate(String id);
    boolean existsProductByIdOrSkuOrEan(String id, String sku, String ean);
}
