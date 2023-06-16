package com.ecommerce.qp.core.business.logic.product.command;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qp.core.business.exceptions.product.ProductWithIdAlreadyExists;
import com.ecommerce.qp.core.business.exceptions.product.ProductNotExistException;
import com.ecommerce.qp.core.business.logic.product.command.features.CreateProduct;
import com.ecommerce.qp.core.business.logic.product.command.features.RemoveProduct;
import com.ecommerce.qp.core.business.logic.product.command.features.UpdateProduct;
import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.ports.in.product.ICommandProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandProduct implements ICommandProduct {

    @Autowired
    private CreateProduct createProduct;

    @Autowired
    private UpdateProduct updateProduct;

    @Autowired
    private RemoveProduct removeProduct;

    @Override
    public Product create(Product product) throws EcommerceBusinessLogicException {
        //  TODO what the best place to catch exception and report log
        return createProduct.createProduct(product);
    }

    @Override
    public Product update(String id, Product product) throws EcommerceBusinessLogicException {
        return updateProduct.updateProduct(id, product);
    }

    @Override
    public void delete(String id) throws EcommerceBusinessLogicException {
        removeProduct.delete(id);
    }


}
