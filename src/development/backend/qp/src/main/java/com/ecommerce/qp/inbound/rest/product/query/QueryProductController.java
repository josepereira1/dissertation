package com.ecommerce.qp.inbound.rest.product.query;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qp.core.ports.in.product.IQueryProduct;
import com.ecommerce.qp.inbound.rest.product.mappers.out.IProductOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
public class QueryProductController implements IQueryProductController{

    @Autowired
    private IQueryProduct queryProduct;

    @Override
    public ResponseEntity readProduct(@Valid @NotBlank String id) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(IProductOutMapper.toReadProductFeatureProductDTO(queryProduct.readProduct(id)));
    }

    @Override
    public ResponseEntity readProductByVisibility(@Valid @NotBlank String id) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(IProductOutMapper.toReadProductFeatureProductDTO(queryProduct.readVisibleAndNotVisibleProduct(id)));
    }
}
