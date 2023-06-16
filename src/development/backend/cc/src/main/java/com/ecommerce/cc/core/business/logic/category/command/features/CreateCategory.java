package com.ecommerce.cc.core.business.logic.category.command.features;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.exceptions.category.CanOnlyCreateRootCategoryException;
import com.ecommerce.cc.core.business.exceptions.category.CategoryAlreadyExistException;
import com.ecommerce.cc.core.business.exceptions.category.RootCategoryNotExistsException;
import com.ecommerce.cc.core.business.logic.category.query.QueryCategory;
import com.ecommerce.cc.core.business.logic.version.command.CommandVersion;
import com.ecommerce.cc.core.business.logs.ILogs;
import com.ecommerce.cc.core.business.resources.CounterMeasure;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSCreateCategories;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.tree.ICQRSUpdateCategoryTree;
import com.ecommerce.cc.core.ports.out.repository.category.ICommandCategoryRepository;
import com.ecommerce.cc.core.ports.out.repository.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;

@Service
public class CreateCategory {

    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Autowired
    private QueryCategory queryCategory;

    @Autowired
    private ICQRSCreateCategories cqrsCreateCategories;

    @Autowired
    private ICQRSUpdateCategoryTree cqrsUpdateCategoryTree;

    @Autowired
    private CommandVersion commandVersion;

    @Autowired
    private ILogs logs;

    @Transactional(rollbackFor = Exception.class)
    public Category createCategory(Category category) throws EcommerceBusinessLogicException {
        if(!category.getName().equals("#root")) throw CanOnlyCreateRootCategoryException.builder().categoryName(category.getName()).build();
        if(queryCategoryRepository.existsByName("#root")) throw CategoryAlreadyExistException.builder().categoryName(category.getName()).build();
        category.setCountermeasure(CounterMeasure.UNLOCKED);
        category = commandCategoryRepository.save(category);
        Category root;
        try {
            root = queryCategory.getRootCategory();
        } catch (EcommerceBusinessLogicException e) {
            e.printStackTrace();
            logs.logWarning("[CQRS] - " + e.getMessage());
            root = new Category();
        }

        Version categoriesVersion = new Version();
        categoriesVersion.setGroupId("c" + category.getId());
        categoriesVersion.setDescription("Version of category with id " + category.getId());
        categoriesVersion.setValue(1);
        commandVersion.createVersion(categoriesVersion);

        Version categoryTreeVersion = commandVersion.generateVersion("tree");
        cqrsUpdateCategoryTree.synchronize(categoryTreeVersion, root);
        cqrsCreateCategories.synchronize(categoriesVersion, category);
        return category;
    }
}
