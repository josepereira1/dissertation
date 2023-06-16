package com.ecommerce.qcvp.core.business.logic.category.category.command.features;

import com.ecommerce.qcvp.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import com.ecommerce.qcvp.core.business.resources.category.category.Product;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.ICommandCategoryRepository;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class CreateProductInCategories {

    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createProductInCategories(Product product, Collection<Long> categoriesIds) throws CategoryNotExistsException {
        HashSet<Category> categories = new HashSet<>();
        for(Long categoryId : categoriesIds){
            Optional<Category> optional = queryCategoryRepository.findById(categoryId);
            if(!optional.isPresent()) throw CategoryNotExistsException.builder().id(categoryId).build();
            Category category = optional.get();
            //  TODO verificar se existe uma melhor abordagem para isto
            boolean notExists = true;
            for(Product elem : category.getProducts())
                if(elem.getId().equals(product.getId())) {
                    notExists = false;
                    continue;
                }
            if(notExists) {
                category.getProducts().add(product);
                /*if (category.getSize() == null) category.setSize(0);
                else category.setSize(category.getSize() + 1);*/
                category.setSize(category.getProducts().size());
            }
            categories.add(category);
        }
        commandCategoryRepository.saveAll(categories);
    }
}
