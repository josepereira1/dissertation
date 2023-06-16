package com.ecommerce.qcvp.outbound.repository.mongo.category.category;

import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.ICommandCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommandCategoryRepository implements ICommandCategoryRepository {

    @Autowired
    private CategoryDAO categoryDAO;


    @Override
    public Category save(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    public List<Category> saveAll(Iterable<Category> categories) {
        return categoryDAO.saveAll(categories);
    }

    @Override
    public void deleteAll(Iterable<Category> categories) {
        categoryDAO.deleteAll(categories);
    }
}
