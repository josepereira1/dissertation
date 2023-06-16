package com.ecommerce.cc.outbound.adapters.postgres.category;

import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.business.resources.product.Product;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.persistence.LockModeType;
import java.util.Optional;

public interface CategoryDAO extends CrudRepository<Category, Long> {
    boolean existsByName(String name);
    <T> Optional<T> findCategoryByName(String name, Class<T> type);

    @Lock(LockModeType.NONE)
    @Query("select c from Category c where c.id = ?1")
    Optional<Product> findOneForRead(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Category c where c.id = ?1")
    Optional<Category> findOneForUpdate(Long id);
}
