package com.ecommerce.cc.core.business.logic.product;

import com.ecommerce.cc.core.business.exceptions.category.CategoryNotExistException;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.out.repository.category.ICommandCategoryRepository;
import com.ecommerce.cc.core.ports.out.repository.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CommonProduct {
    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    public Product updateProductCategories(Set<Long> addCategoryIds, Set<Long> removeCategoryIds, Product product) throws CategoryNotExistException {
        Set<Category> removeCategories = new HashSet<>(), addCategories = new HashSet<>();
        if(removeCategoryIds != null && removeCategoryIds.size() > 0) removeCategories = removeProductOnCategoriesRecords(removeCategoryIds, product);
        if(addCategoryIds != null && addCategoryIds.size() > 0) addCategories = addProductOnCategoriesRecords(addCategoryIds, product);

        //  only save, if the above methods are executed successfully
        if(addCategoryIds != null && addCategoryIds.size() > 0) commandCategoryRepository.saveAll(removeCategories);
        if(removeCategoryIds != null && removeCategoryIds.size() > 0) commandCategoryRepository.saveAll(addCategories);

        return product;
    }

    private Set<Category> addProductOnCategoriesRecords(Set<Long> productAndCategoryIds, Product product) throws CategoryNotExistException {
        Long categoryId; Category category;
        Iterator<Long> it = productAndCategoryIds.iterator();
        HashSet<Category> categories = new HashSet<>();
        while (it.hasNext()){
            categoryId = it.next();
            Optional<Category> optional = queryCategoryRepository.findOneForUpdate(categoryId);
            if(!optional.isPresent()) throw CategoryNotExistException.builder().id(categoryId).build();
            category = optional.get();
            category.getProducts().add(product);
            categories.add(category);
            //commandCategoryRepository.save(category);
        }

        //commandCategoryRepository.saveAll(categories);
        return categories;
    }

    private Set<Category> removeProductOnCategoriesRecords(Set<Long> productAndCategoryIds, Product product) throws CategoryNotExistException {
        Optional<Category> optional; Category category;
        HashSet<Category> categories = new HashSet<>();
        for(Long elem : productAndCategoryIds){
            optional = queryCategoryRepository.findOneForUpdate(elem);
            if(!optional.isPresent()) throw CategoryNotExistException.builder().id(elem).build();
            category = optional.get();
            category.getProducts().remove(product);
            categories.add(category);   //  add - O(1)
        }
        //commandCategoryRepository.saveAll(categories);
        return categories;
    }

    public static Collection<Long> getCategoriesIds(Collection<Category> categories){
        HashSet<Long> tmp = new HashSet<>();
        for(Category category : categories) tmp.add(category.getId());
        return tmp;
    }
}
