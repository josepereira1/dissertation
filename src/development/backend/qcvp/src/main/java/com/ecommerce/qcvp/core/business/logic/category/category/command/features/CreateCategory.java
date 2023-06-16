package com.ecommerce.qcvp.core.business.logic.category.category.command.features;

import com.ecommerce.qcvp.core.business.exceptions.category.CategoryAlreadyExistsException;
import com.ecommerce.qcvp.core.business.exceptions.category.RootCategoryAlreadyExistsException;
import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.ICommandCategoryRepository;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Service
public class CreateCategory {

    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Transactional(rollbackFor = Exception.class)
    public Category createCategory(Category category) throws CategoryAlreadyExistsException, RootCategoryAlreadyExistsException {
        Collection<Long> ids = new HashSet<>();
        if(category.getName().equals("#root") && queryCategoryRepository.existsCategoriesByName("#root"))
            throw RootCategoryAlreadyExistsException.builder().build();
        ids.add(category.getId());
        category.setSize(0);
        category.setProducts(new ArrayList<>());
        if(queryCategoryRepository.existsAllById(ids)) throw CategoryAlreadyExistsException.builder().build();
        return commandCategoryRepository.save(category);
    }
}
