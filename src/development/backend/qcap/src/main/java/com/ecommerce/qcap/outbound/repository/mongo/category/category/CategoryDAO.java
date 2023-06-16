package com.ecommerce.qcap.outbound.repository.mongo.category.category;

import com.ecommerce.qcap.core.business.resources.CounterMeasure;
import com.ecommerce.qcap.core.business.resources.category.category.Category;
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
    @Query(value = "{ 'id' : ?0}", fields = "{'name': 1, 'size': 1 }")
    Optional<Category> findCategoryNameAndSize(Long id);
    @Query(value = "{ 'id' : ?0, countermeasure: ?1}", fields = "{name: 1, size: 1, products: {$slice: [?2, ?3]}}")
    Optional<Category> findCategoryByIdAndPageAndProductsPerPage(Long id, CounterMeasure counterMeasure, int page, int productsPerPage);
}
