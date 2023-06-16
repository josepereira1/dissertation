package com.ecommerce.qcvp.outbound.repository.mongo.category.category;

import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryDAO extends MongoRepository<Category, Long> {
    Iterable<Category> findAllById(Iterable<Long> iterable);
    void deleteAll(Iterable<? extends Category> iterable);
    boolean existsAllById(Iterable<Long> ids);
    boolean existsCategoryByName(String name);
    @Query(value = "{ 'id' : ?0}", fields = "{name: 1, size: 1, products: {$slice: [?1, ?2]}}")
    Optional<Category> findCategoryProductsByPagination(Long id, int page, int productsPerPage);
}
