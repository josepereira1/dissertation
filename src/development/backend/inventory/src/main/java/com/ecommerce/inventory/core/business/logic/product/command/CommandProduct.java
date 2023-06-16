package com.ecommerce.inventory.core.business.logic.product.command;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.inventory.core.business.logic.product.command.features.StockManagement;
import com.ecommerce.inventory.core.business.logic.product.command.features.CreateProduct;
import com.ecommerce.inventory.core.business.logic.product.command.features.DeleteProduct;
import com.ecommerce.inventory.core.business.logic.product.command.features.UpdateProduct;
import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.ports.in.product.ICommandProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class CommandProduct implements ICommandProduct {

    @Autowired
    private CreateProduct createProduct;

    @Autowired
    private UpdateProduct updateProduct;

    @Autowired
    private DeleteProduct deleteProduct;

    @Autowired
    private StockManagement stockManagement;

    @Override
    public Product createProduct(Product product) throws EcommerceBusinessLogicException {
        return createProduct.createProduct(product);
    }

    @Override
    public Product updateProduct(String id, Product product) throws EcommerceBusinessLogicException {
        return updateProduct.updateProduct(id, product);
    }

    @Override
    public Collection<Product> addStock(List<Product> products) throws EcommerceBusinessLogicException {
        return stockManagement.addStock(products);
    }

    @Override
    public Collection<Product> removeStock(List<Product> products) throws EcommerceBusinessLogicException {
        return stockManagement.removeStock(products);
    }

    @Override
    public void deleteProduct(String id) throws EcommerceBusinessLogicException{
        deleteProduct.deleteProduct(id);
    }
}
