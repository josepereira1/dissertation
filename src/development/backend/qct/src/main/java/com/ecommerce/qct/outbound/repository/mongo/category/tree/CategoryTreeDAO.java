package com.ecommerce.qct.outbound.repository.mongo.category.tree;

import com.ecommerce.qct.core.business.resources.category.tree.CategoryTree;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryTreeDAO extends MongoRepository<CategoryTree, Long> {
    boolean existsCategoryTreeByName(String name);
    Optional<CategoryTree> findCategoryTreeByName(String name);
    void deleteCategoryTreeByName(String name);
}
