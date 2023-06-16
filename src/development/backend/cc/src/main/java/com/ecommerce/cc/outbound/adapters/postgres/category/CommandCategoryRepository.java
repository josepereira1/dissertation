package com.ecommerce.cc.outbound.adapters.postgres.category;

import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.ports.out.repository.category.ICommandCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;

@Service
public class CommandCategoryRepository implements ICommandCategoryRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public Category save(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    public Iterable<Category> saveAll(Iterable<Category> categories){
        return categoryDAO.saveAll(categories);
    }

    @Override
    public void delete(Category category){
        categoryDAO.delete(category);
    }
}
