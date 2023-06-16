package com.ecommerce.qcap.core.business.logic.category.category.command.features;

import com.ecommerce.qcap.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcap.core.business.resources.category.category.Category;
import com.ecommerce.qcap.core.ports.out.repository.category.category.ICommandCategoryRepository;
import com.ecommerce.qcap.core.ports.out.repository.category.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeleteCategories {

    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Transactional(rollbackFor = Exception.class)
    public void deleteCategories(Collection<Long> ids) throws CategoryNotExistsException {
        Collection<Category> categories = StreamSupport.stream(queryCategoryRepository.findAllById(ids).spliterator(), false).collect(Collectors.toSet());
        if(ids.size() != categories.size()) throw CategoryNotExistsException.builder().build();
        commandCategoryRepository.deleteAll(categories);
    }
}
