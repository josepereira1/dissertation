package com.ecommerce.sc.core.business.logic.product.command;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.logic.product.command.features.DeleteProduct;
import com.ecommerce.sc.core.business.logic.product.command.features.UpdateProduct;
import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.ports.in.product.ICommandProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.sc.core.business.logic.product.command.features.CreateProduct;

@Service
public class CommandProduct implements ICommandProduct {

    @Autowired
    private CreateProduct createProduct;

    @Autowired
    private UpdateProduct updateProduct;

    @Autowired
    private DeleteProduct deleteProduct;

    @Override
    public Product createProduct(Product product) throws EcommerceBusinessLogicException {
        return createProduct.createProduct(product);
    }

    @Override
    public Product updateProduct(String productId, Product product) throws EcommerceBusinessLogicException {
        return updateProduct.updateProduct(productId, product);
    }

    @Override
    public void deleteProduct(String productId) throws EcommerceBusinessLogicException {
        deleteProduct.deleteProduct(productId);
    }
}
