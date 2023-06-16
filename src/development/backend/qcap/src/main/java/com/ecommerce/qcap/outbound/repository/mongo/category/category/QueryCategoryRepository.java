package com.ecommerce.qcap.outbound.repository.mongo.category.category;

import com.ecommerce.qcap.core.business.resources.CounterMeasure;
import com.ecommerce.qcap.core.business.resources.category.category.Category;
import com.ecommerce.qcap.core.business.resources.category.category.Visibility;
import com.ecommerce.qcap.core.ports.out.repository.category.category.IQueryCategoryRepository;
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

    @Autowired
    private MongoTemplate mongoTemplate;

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
    public Optional<Category> findCategoryByIdAndCountermeasureAndPageAndProductsPerPage(Long id, CounterMeasure counterMeasure, int page, int productsPerPage) {
        Aggregation aggregation = Aggregation.newAggregation(
                match(new Criteria("_id").is(id)),
                project().and(
                        filter("products")
                                .as("pro")
                                .by(valueOf("pro.countermeasure").equalToValue(counterMeasure)))
                        .as("products"),
                project().and("products").slice(page*productsPerPage, (page*productsPerPage) - productsPerPage).as("products")
        ).withOptions(AggregationOptions.builder().allowDiskUse(Boolean.TRUE).build());
        AggregationResults<Category> res = mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Category.class), Category.class);
        Optional<Category> optional = categoryDAO.findCategoryNameAndSize(id);
        if(!optional.isPresent() || res.getMappedResults() != null && res.getMappedResults().size() > 0) {
            Category category = res.getUniqueMappedResult();
            category.setName(optional.get().getName());
            category.setSize(optional.get().getSize());
            return Optional.of(category);
        }
        else return Optional.empty();
    }

    @Override
    public Optional<Category> findCategoryByIdAndAndCountermeasureAndPageAndProductsPerPageAndVisibility(Long id, CounterMeasure counterMeasure, int page, int productsPerPage, Visibility visibility) {
        Aggregation aggregation = Aggregation.newAggregation(
                match(new Criteria("_id").is(id)),
                project().and(
                        filter("products")
                                .as("pro")
                                .by(valueOf("pro.visibility").equalToValue(visibility)))
                        .as("products"),
                project().and(
                        filter("products")
                                .as("pro")
                                .by(valueOf("pro.countermeasure").equalToValue(counterMeasure)))
                        .as("products"),
                project().and("products").slice(page*productsPerPage, (page*productsPerPage) - productsPerPage).as("products")
        ).withOptions(AggregationOptions.builder().allowDiskUse(Boolean.TRUE).build());
        AggregationResults<Category> res = mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Category.class), Category.class);
        Optional<Category> optional = categoryDAO.findCategoryNameAndSize(id);
        if(optional.isPresent()) {
            Category category;
            if(res.getMappedResults() != null && res.getMappedResults().size() > 0)
                category = res.getUniqueMappedResult();
            else {
                category = new Category();
                category.setId(id);
            }
            category.setName(optional.get().getName());
            category.setSize(optional.get().getSize());
            return Optional.of(category);
        }
        else return Optional.empty();
    }

    @Override
    public boolean existsCategoriesByName(String name) {
        return categoryDAO.existsCategoryByName(name);
    }
}
