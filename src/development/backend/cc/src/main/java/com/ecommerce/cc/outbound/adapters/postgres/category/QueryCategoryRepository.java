package com.ecommerce.cc.outbound.adapters.postgres.category;

import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.ports.out.repository.category.IQueryCategoryRepository;
import com.ecommerce.cc.outbound.adapters.postgres.category.mappers.out.projections.CategoryIdAndNameChildCategories;
import com.ecommerce.cc.outbound.adapters.postgres.category.mappers.out.ICategoryOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Optional<Category> findOneForRead(String id) {
        return findOneForRead(id);
    }

    @Override
    public Optional<Category> findOneForUpdate(Long id) {
        return categoryDAO.findOneForUpdate(id);
    }

    @Override
    public boolean existsByName(String name){
        return categoryDAO.existsByName(name);
    }

    @Override
    public Optional<Category> findCategoryByNameIdAndNameAndChildCategories(String name) {
        Optional<CategoryIdAndNameChildCategories> optional = categoryDAO.findCategoryByName(name, CategoryIdAndNameChildCategories.class);
        if(optional.isPresent()) return Optional.of(ICategoryOutMapper.toCategory(optional.get()));
        else return Optional.empty();
    }
}
