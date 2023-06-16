package com.ecommerce.qcvp.outbound.repository.mongo.category.category;

import com.ecommerce.qcvp.core.business.resources.CounterMeasure;
import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import com.ecommerce.qcvp.core.business.resources.category.category.Visibility;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.ArrayOperators.Filter.filter;
import static org.springframework.data.mongodb.core.aggregation.ComparisonOperators.Eq.valueOf;
import java.util.Optional;

@Service
public class QueryCategoryRepository implements IQueryCategoryRepository {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryDAO.findById(id);
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Long> ids) {
        return categoryDAO.findAllById(ids);
    }

    @Override
    public boolean existsAllById(Iterable<Long> ids) {
        return categoryDAO.existsAllById(ids);
    }

    @Override
    public Optional<Category> findCategoryProductsByPagination(Long id, int page, int productsPerPage) {
        return categoryDAO.findCategoryProductsByPagination(id, page, productsPerPage);
    }

    @Override
    public boolean existsCategoriesByName(String name) {
        return categoryDAO.existsCategoryByName(name);
    }
}
