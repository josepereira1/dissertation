package com.ecommerce.cc.core.business.logic.category.command.features;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.exceptions.category.CategoryAlreadyExistException;
import com.ecommerce.cc.core.business.exceptions.category.CategoryNameIsInvalidException;
import com.ecommerce.cc.core.business.exceptions.category.CategoryNotExistException;
import com.ecommerce.cc.core.business.logic.category.query.QueryCategory;
import com.ecommerce.cc.core.business.logic.version.command.CommandVersion;
import com.ecommerce.cc.core.business.logs.ILogs;
import com.ecommerce.cc.core.business.resources.CounterMeasure;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.business.resources.category.CategoryComparatorByName;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSCreateCategories;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.tree.ICQRSUpdateCategoryTree;
import com.ecommerce.cc.core.ports.out.repository.category.ICommandCategoryRepository;
import com.ecommerce.cc.core.ports.out.repository.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.TreeSet;

@Service
public class UpdateCategorySubcategories {

    @Autowired
    private CommandCategoryCommonMethods commandCategoryCommonMethods;

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
    public Collection<Category> updateCategorySubcategories(Long parentCategoryId, Collection<Category> subcategory) throws EcommerceBusinessLogicException {
        Category parentCategory;
        Optional<Category> optional = queryCategoryRepository.findOneForUpdate(parentCategoryId);
        if(!optional.isPresent())throw new CategoryNotExistException(parentCategoryId, null);
        parentCategory = optional.get();
        TreeSet<Category> res = new TreeSet<>();
        for(Category elem : subcategory) {
            res.add(addSubcategoryToParentCategory(elem, parentCategory));
        }

        TreeSet<Category> newCategories = new TreeSet<>();
        for(Category currentCategory : parentCategory.getChildCategories()){
            for(Category inputCategory : subcategory){
                if(currentCategory.getName().equals(inputCategory.getName())) {
                    Category tmp = new Category();
                    tmp.setId(currentCategory.getId());
                    tmp.setName(currentCategory.getName());
                    newCategories.add(tmp);
                    break;
                }
            }
        }

        Version categoriesVersion;

        for(Category category : newCategories){
            categoriesVersion = new Version();
            categoriesVersion.setGroupId("c" + category.getId());
            categoriesVersion.setDescription("Version of category with id " + category.getId());
            categoriesVersion.setValue(1);
            categoriesVersion = commandVersion.createVersion(categoriesVersion);

            cqrsCreateCategories.synchronize(categoriesVersion, category);
        }
        //cqrsCreateCategories.synchronize(categoriesVersion, res.stream().findFirst().get().getChildCategories());
        Category root;
        try {
            root = queryCategory.getRootCategory();
        } catch (EcommerceBusinessLogicException e) {
            e.printStackTrace();
            logs.logWarning("[CQRS] - " + e.getMessage());
            root = new Category();
        }
        Version categoryTreeVersion = commandVersion.generateVersion("tree");
        cqrsUpdateCategoryTree.synchronize(categoryTreeVersion, root);
        return res;
    }

    private Category addSubcategoryToParentCategory(Category subcategory, Category parentCategory) throws CategoryAlreadyExistException, CategoryNameIsInvalidException {
        Category category;
        if(subcategory.getName() != null && !subcategory.getName().equals("#root")){
            category = setupCategory(subcategory.getName(), parentCategory);
            parentCategory = addCategoryAsChildOfParentCategory(parentCategory, category);
            return commandCategoryRepository.save(parentCategory);
        }else throw CategoryNameIsInvalidException.builder().categoryName(subcategory.getName()).build();
    }

    private Category setupCategory(String name, Category parentCategory){
        Category category = new Category();
        category.setName(name);
        category.setParentCategory(parentCategory);
        category.setChildCategories(new TreeSet<>());
        category.setCountermeasure(CounterMeasure.UNLOCKED);
        return category;
    }

    private Category addCategoryAsChildOfParentCategory(Category parentCategory, Category childCategory) throws CategoryAlreadyExistException, CategoryNameIsInvalidException {
        if(parentCategory.getChildCategories() == null || parentCategory.getChildCategories().size() == 0){
            TreeSet<Category> subcategories = new TreeSet(new CategoryComparatorByName());
            subcategories.add(childCategory);
            parentCategory.setChildCategories(subcategories);
        }else {
            if(commandCategoryCommonMethods.childCategoryAlreadyExist(parentCategory, childCategory)) throw CategoryAlreadyExistException.builder().categoryName(childCategory.getName()).build();
            parentCategory.getChildCategories().add(childCategory);
        }
        return parentCategory;
    }
}
