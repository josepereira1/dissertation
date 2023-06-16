package com.ecommerce.qcvp.core.business.logic.category.category.command.features;

import com.ecommerce.qcvp.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.ICommandCategoryRepository;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateCategoryName {

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Transactional(rollbackFor = Exception.class)
    public Category updateCategoryName(Long id, String name) throws CategoryNotExistsException {
        Optional<Category> optional = queryCategoryRepository.findById(id);
        if(!optional.isPresent()) throw CategoryNotExistsException.builder().id(id).build();
        Category category = optional.get();
        category.setName(name);
        return commandCategoryRepository.save(category);
    }
}
