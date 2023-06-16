package com.ecommerce.inventory.inbound.rest.product.command;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.inventory.core.ports.in.product.ICommandProduct;
import com.ecommerce.inventory.inbound.rest.product.mappers.in.IProductInMapper;
import com.ecommerce.inventory.inbound.rest.product.mappers.in.dtos.UpdateProductFeatureProductDTO;
import com.ecommerce.inventory.inbound.rest.product.mappers.out.IProductOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandProductController implements ICommandProductController{

    @Autowired
    private ICommandProduct commandProduct;

    @Override
    public ResponseEntity updateProductStock(String id, UpdateProductFeatureProductDTO product) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(IProductOutMapper.toProductDTO(commandProduct.updateProduct(id, IProductInMapper.toProduct(product))));
    }
}
