package com.ecommerce.cc.core.business.logic.product.command.features;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.exceptions.category.CategoryNotExistException;
import com.ecommerce.cc.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.cc.core.business.logic.product.CommonProduct;
import com.ecommerce.cc.core.business.logic.version.command.CommandVersion;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSUpdateProductInCategories;
import com.ecommerce.cc.core.ports.out.repository.category.IQueryCategoryRepository;
import com.ecommerce.cc.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.cc.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class ManagementCategoriesOfProduct {

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private ICQRSUpdateProductInCategories cqrsUpdateProductInCategories;

    @Autowired
    private CommandVersion commandVersion;


    @Transactional(rollbackFor = Exception.class)
    public Product addCategories(String id, Set<Long> categories) throws EcommerceBusinessLogicException{
        return generic(id, categories, true);
    }

    @Transactional(rollbackFor = Exception.class)
    public Product removeCategories(String id, Set<Long> categories) throws EcommerceBusinessLogicException{
        return generic(id, categories, false);
    }

    private Product generic(String id, Set<Long> categories, boolean toAdd) throws EcommerceBusinessLogicException {
        Optional<Product> optional1 = queryProductRepository.findOneForUpdate(id);
        if(!optional1.isPresent())
            throw ProductWithThatIdNotExistException.builder().id(id).build();

        Product product = optional1.get();
        Collection<Long> currentCategories = CommonProduct.getCategoriesIds(product.getCategories());
        HashSet<Category> tmp1 = new HashSet<>();

        if(categories != null && !categories.isEmpty()) {
            for (Long categoryId : categories) {
                Optional<Category> optional2 = queryCategoryRepository.findOneForUpdate(categoryId);
                if (!optional2.isPresent())
                    throw CategoryNotExistException.builder().id(categoryId).build();
                Category category = optional2.get();
                tmp1.add(category);
            }
            if(toAdd)
                product.getCategories().addAll(tmp1);
            else
                product.getCategories().removeAll(tmp1);

            commandProductRepository.save(product);
            Version categoriesVersion;
            TreeSet<Long> categoriesToSend;

            for(Long categoryId : categories){
                categoriesToSend = new TreeSet<>();
                categoriesToSend.add(categoryId);
                categoriesVersion = commandVersion.generateVersion("c" + categoryId);
                if(toAdd)
                    cqrsUpdateProductInCategories.synchronize(categoriesVersion, product, currentCategories, categoriesToSend, new TreeSet<>());
                else
                    cqrsUpdateProductInCategories.synchronize(categoriesVersion, product, currentCategories, new TreeSet<>(), categoriesToSend);
            }
        }
        return product;
    }
}
