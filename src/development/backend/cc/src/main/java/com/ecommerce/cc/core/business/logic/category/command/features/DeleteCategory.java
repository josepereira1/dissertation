package com.ecommerce.cc.core.business.logic.category.command.features;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.exceptions.category.CategoryNotExistException;
import com.ecommerce.cc.core.business.logic.category.query.QueryCategory;
import com.ecommerce.cc.core.business.logic.version.command.CommandVersion;
import com.ecommerce.cc.core.business.logs.ILogs;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSDeleteCategories;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.tree.ICQRSUpdateCategoryTree;
import com.ecommerce.cc.core.ports.out.repository.category.ICommandCategoryRepository;
import com.ecommerce.cc.core.ports.out.repository.category.IQueryCategoryRepository;
import com.ecommerce.cc.core.ports.out.repository.product.ICommandProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DeleteCategory {

    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private QueryCategory queryCategory;

    @Autowired
    private ICQRSDeleteCategories cqrsDeleteCategories;

    @Autowired
    private ICQRSUpdateCategoryTree cqrsUpdateCategoryTree;

    @Autowired
    private CommandVersion commandVersion;

    @Autowired
    private ILogs logs;

    @Transactional(rollbackFor = Exception.class)
    public Category deleteCategory(Long id) throws EcommerceBusinessLogicException {
        Optional<Category> optionalCategory = queryCategoryRepository.findOneForUpdate(id);
        if(!optionalCategory.isPresent()) throw CategoryNotExistException.builder().id(id).build();
        Category firstCategory = optionalCategory.get(), parentCategory = null;
        Optional<Category> optionalParentCategory = null;
        if(firstCategory.getParentCategory() != null) optionalParentCategory = queryCategoryRepository.findOneForUpdate(firstCategory.getParentCategory().getId());
        if(optionalParentCategory != null && optionalParentCategory.isPresent()) parentCategory = optionalParentCategory.get();
        HashSet<Product> res = new HashSet<>();
        for(Category category : getChildCategories(optionalCategory.get())){
            Set<Product> products = category.getProducts();
            for(Product product : products){
                product.getCategories().remove(category);
                res.add(product);
            }
        }
        commandProductRepository.saveAll(res);
        //  após apagar as relações entre produtos e categorias, posso apagar a categoria, isto é necessário, uma vez que, quem manda na relação é o produto
        commandCategoryRepository.delete(firstCategory);
        synchronizeCQRS(firstCategory);
        return parentCategory;    //  return the parent
    }

    private Set<Category> getChildCategories(Category category){
        HashSet<Category> categories = new HashSet<>();
        categories.add(category);

        for(Category childCategory : category.getChildCategories()){
            categories.addAll(getChildCategories(childCategory));
        }

        return categories;
    }

    private Collection<String> getChildCategoriesIds(Category category, Collection<String> ids){
        for(Category child : category.getChildCategories()){
            ids.add(String.valueOf(child.getId()));
            ids.addAll(getChildCategoriesIds(child, ids));
        }
        return ids;
    }

    private void synchronizeCQRS(Category category) throws EcommerceBusinessLogicException {
        Collection<String> ids = new HashSet<>();
        ids = getChildCategoriesIds(category, ids);
        ids.add(String.valueOf(category.getId()));    //  add main category to delete
        Version categoriesVersion = commandVersion.generateVersion("c" + category.getId());
        cqrsDeleteCategories.synchronize(categoriesVersion, ids);
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
    }
}
