package com.ecommerce.cc.inbound.adapters.rest.product.query;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.ports.in.product.IQueryProduct;
import com.ecommerce.cc.inbound.adapters.rest.product.mappers.out.IProductOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

@RestController
public class QueryProductController implements IQueryProductController {

    @Autowired
    private IQueryProduct queryProduct;

    @Override
    public ResponseEntity readProductCategories(String id) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(queryProduct.readProductCategories(id).stream().map(IProductOutMapper::toCategoryDTO).collect(Collectors.toSet()));
    }
}
