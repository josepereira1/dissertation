package com.ecommerce.qcvp.core.business.logic.category.category.command.features;

import com.ecommerce.qcvp.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import com.ecommerce.qcvp.core.business.resources.category.category.Product;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.ICommandCategoryRepository;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeleteProductInCategories {

    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Transactional(rollbackFor = Exception.class)
    public void deleteProductInCategories(String productId, Collection<Long> categoryIds) throws CategoryNotExistsException {
        Collection<Category> categories = StreamSupport.stream(queryCategoryRepository.findAllById(categoryIds).spliterator(), false).collect(Collectors.toSet());
        if(categories.size() != categoryIds.size()) throw CategoryNotExistsException.builder().build();
        for(Category category : categories){
            List<Product> products = category.getProducts();
            for(int i = 0; i < products.size(); i++)
                if (products.get(i).getId().equals(productId))
                    products.remove(i);
            if(category.getSize() == null) category.setSize(0);
            else category.setSize(category.getProducts().size());
            category.setProducts(products);
            categories.add(category);
        }
        commandCategoryRepository.saveAll(categories);
    }
}
