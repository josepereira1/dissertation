package com.ecommerce.cc.core.business.logic.category.command.features;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.exceptions.category.CategoryAlreadyExistException;
import com.ecommerce.cc.core.business.exceptions.category.CategoryNameIsInvalidException;
import com.ecommerce.cc.core.business.exceptions.category.CategoryNotExistException;
import com.ecommerce.cc.core.business.exceptions.version.VersionNotExistsException;
import com.ecommerce.cc.core.business.logic.category.query.QueryCategory;
import com.ecommerce.cc.core.business.logic.version.command.CommandVersion;
import com.ecommerce.cc.core.business.logs.ILogs;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSUpdateCategoryName;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.tree.ICQRSUpdateCategoryTree;
import com.ecommerce.cc.core.ports.out.repository.category.ICommandCategoryRepository;
import com.ecommerce.cc.core.ports.out.repository.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateCategoryName {

    @Autowired
    private CommandCategoryCommonMethods commandCategoryCommonMethods;

    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Autowired
    private QueryCategory queryCategory;

    @Autowired
    private ICQRSUpdateCategoryName cqrsUpdateCategoryName;

    @Autowired
    private ICQRSUpdateCategoryTree cqrsUpdateCategoryTree;

    @Autowired
    private CommandVersion commandVersion;

    @Autowired
    private ILogs logs;

    @Transactional(rollbackFor = Exception.class)
    public Category updateCategoryName(Long id, Category updatedCategory) throws EcommerceBusinessLogicException {
        if(updatedCategory.getName().equals("#root")) throw CategoryNameIsInvalidException.builder().categoryName("#root").build();
        Optional<Category> optional = queryCategoryRepository.findOneForUpdate(id);
        if(!optional.isPresent()) throw CategoryNotExistException.builder().id(id).build();
        Category nonUpdatedCategory = optional.get();
        if(commandCategoryCommonMethods.childCategoryAlreadyExist(nonUpdatedCategory.getParentCategory(), updatedCategory)) throw CategoryAlreadyExistException.builder().categoryName(updatedCategory.getName()).build();
        nonUpdatedCategory.setName(updatedCategory.getName());
        commandCategoryRepository.save(nonUpdatedCategory);
        Category root;
        try {
            root = queryCategory.getRootCategory();
        } catch (EcommerceBusinessLogicException e) {
            e.printStackTrace();
            logs.logWarning("[CQRS] - " + e.getMessage());
            root = new Category();
        }
        Version categoriesVersion = commandVersion.generateVersion("c" + id);
        Version categoryTreeVersion = commandVersion.generateVersion("tree");
        cqrsUpdateCategoryName.synchronize(categoriesVersion, id, updatedCategory.getName());
        cqrsUpdateCategoryTree.synchronize(categoryTreeVersion, root);
        return nonUpdatedCategory;
    }
}
