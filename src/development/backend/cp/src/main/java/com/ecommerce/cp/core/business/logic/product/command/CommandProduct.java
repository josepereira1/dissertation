package com.ecommerce.cp.core.business.logic.product.command;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.logic.product.command.features.CreateProduct;
import com.ecommerce.cp.core.business.logic.product.command.features.DeleteProduct;
import com.ecommerce.cp.core.business.logic.product.command.features.UpdateProduct;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.ports.in.product.ICommandProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommandProduct implements ICommandProduct {

    @Autowired
    private CreateProduct createProduct;

    @Autowired
    private UpdateProduct updateProduct;

    @Autowired
    private DeleteProduct deleteProduct;

    @Override
    public Product createProduct(Product product) throws EcommerceBusinessLogicException{
        return createProduct.createProduct(product);
    }

    @Override
    public Product updateProduct(String id, Product product) throws EcommerceBusinessLogicException {
        return updateProduct.updateProduct(id,  product);
    }

    @Override
    public void deleteProduct(String id) throws EcommerceBusinessLogicException {
        deleteProduct.deleteProduct(id);
    }
}
