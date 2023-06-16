package com.ecommerce.inventory.inbound.rest.product.query;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.inventory.core.ports.in.product.IQueryProduct;
import com.ecommerce.inventory.inbound.rest.product.mappers.out.IProductOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryProductController implements IQueryProductController {

    @Autowired
    private IQueryProduct queryProduct;

    @Override
    public ResponseEntity readProduct(String id) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(IProductOutMapper.toProductDTO(queryProduct.readProduct(id)));
    }
}
