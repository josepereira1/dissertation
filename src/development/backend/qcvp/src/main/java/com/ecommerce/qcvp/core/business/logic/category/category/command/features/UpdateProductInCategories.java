package com.ecommerce.qcvp.core.business.logic.category.category.command.features;

import com.ecommerce.qcvp.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcvp.core.business.exceptions.category.ProductAlreadyExistsException;
import com.ecommerce.qcvp.core.business.exceptions.category.ProductNotExistsException;
import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import com.ecommerce.qcvp.core.business.resources.category.category.Product;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.ICommandCategoryRepository;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class UpdateProductInCategories {

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Autowired
    private CreateProductInCategories createProductInCategories;

    @Autowired
    private DeleteProductInCategories deleteProductInCategories;

    @Transactional(rollbackFor = Exception.class)
    public void updateProductInCategories(Product product, Collection<Long> categoriesIds, Collection<Long> addCategoryIds, Collection<Long> removeCategoryIds) throws CategoryNotExistsException, ProductNotExistsException, ProductAlreadyExistsException {
        Set<Category> categories = new HashSet<>();
        //  TODO usar findAlls
        if(categoriesIds != null && categoriesIds.size() > 0) {
            for (Long categoryId : categoriesIds) {
                Optional<Category> optional = queryCategoryRepository.findById(categoryId);
                if (!optional.isPresent())
                    throw CategoryNotExistsException.builder().id(categoryId).build();
                Category category = optional.get();
                List<Product> products = category.getProducts();
                boolean notExists = true;
                for(Product elem : products)
                    if(elem.getId().equals(product.getId())) {
                        updateProduct(product, elem);
                        notExists = false;
                        break;
                    }
                if(notExists) {     //  foi removido/(não criado) devido à visibilidade
                    createProductInCategories.createProductInCategories(product, categoriesIds);
                }else{
                    categories.add(category);
                }
            }
        }
        //  TODO este save tem que ser aqui, para que, os deletes dos produtos nas categorias sejam feitos,
        //  uma melhoria a esta funcionalidade, poderá ser separar o update do produto do delete e add de categorias,
        //  passarem a ser 3 funcionalidades distintas
        commandCategoryRepository.saveAll(categories);
        if(addCategoryIds != null && addCategoryIds.size() > 0) createProductInCategories.createProductInCategories(product, addCategoryIds);
        if(removeCategoryIds != null && removeCategoryIds.size() > 0) deleteProductInCategories.deleteProductInCategories(product.getId(), removeCategoryIds);
    }

    private Product updateProduct(Product productUpdated, Product product){
        if(productUpdated.getName() != null) product.setName(productUpdated.getName());
        if(productUpdated.getShortDetails() != null) product.setShortDetails(productUpdated.getShortDetails());
        if(productUpdated.getCurrency() != null) product.setCurrency(productUpdated.getCurrency());
        if(productUpdated.getDiscountPercentage() != null) product.setDiscountPercentage(productUpdated.getDiscountPercentage());
        if(productUpdated.getAmountInDiscount() != null) product.setAmountInDiscount(productUpdated.getAmountInDiscount());
        if(productUpdated.getFinalPrice() != null) product.setFinalPrice(productUpdated.getFinalPrice());
        if(productUpdated.getStockStatus() != null) product.setStockStatus(productUpdated.getStockStatus());
        if(productUpdated.getLinks() != null) product.setLinks(productUpdated.getLinks());
        if(productUpdated.getVisibility() != null) product.setVisibility(productUpdated.getVisibility());
        if(productUpdated.getPn() != null) product.setPn(productUpdated.getPn());
        if(productUpdated.getOwner() != null) product.setOwner(productUpdated.getOwner());
        if(productUpdated.getCountermeasure() != null) product.setCountermeasure(productUpdated.getCountermeasure());
        return product;
    }
}
