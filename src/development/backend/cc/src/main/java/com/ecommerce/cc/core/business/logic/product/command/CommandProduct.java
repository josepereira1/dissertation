package com.ecommerce.cc.core.business.logic.product.command;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.logic.product.command.features.CreateProduct;
import com.ecommerce.cc.core.business.logic.product.command.features.DeleteProduct;
import com.ecommerce.cc.core.business.logic.product.command.features.ManagementCategoriesOfProduct;
import com.ecommerce.cc.core.business.logic.product.command.features.UpdateProduct;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.in.product.ICommandProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;;

@Service
public class CommandProduct implements ICommandProduct {

    @Autowired
    private CreateProduct createProduct;

    @Autowired
    private UpdateProduct updateProduct;

    @Autowired
    private DeleteProduct deleteProduct;

    @Autowired
    private ManagementCategoriesOfProduct managementCategoriesOfProduct;

    @Override
    public Product createProduct(Product product, Set<Long> categoryIds) throws EcommerceBusinessLogicException {
        return createProduct.create(product, categoryIds);
    }

    @Override
    public Product updateProduct(String id, Product product) throws EcommerceBusinessLogicException {
        return updateProduct.updateProduct(id,  product);
    }

    @Override
    public void deleteProduct(String id) throws EcommerceBusinessLogicException {
        deleteProduct.deleteProduct(id);
    }

    @Override
    public Product addCategories(String id, Set<Long> categories) throws EcommerceBusinessLogicException {
        return managementCategoriesOfProduct.addCategories(id, categories);
    }

    @Override
    public Product removeCategories(String id, Set<Long> categories) throws EcommerceBusinessLogicException {
        return managementCategoriesOfProduct.removeCategories(id, categories);
    }
}
