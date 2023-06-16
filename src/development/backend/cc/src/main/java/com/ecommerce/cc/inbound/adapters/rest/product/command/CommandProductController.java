package com.ecommerce.cc.inbound.adapters.rest.product.command;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.ports.in.product.ICommandProduct;
import com.ecommerce.cc.inbound.adapters.rest.product.mappers.out.IProductOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

@RestController
public class CommandProductController implements ICommandProductController {

    @Autowired
    private ICommandProduct commandProduct;

    @Override
    public ResponseEntity addCategories(String id, Set<Long> categoryIds) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(commandProduct.addCategories(id, categoryIds).getCategories().stream().map(IProductOutMapper::toCategoryDTO));
    }

    @Override
    public ResponseEntity removeCategories(String id, Set<Long> categoryIds) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(commandProduct.removeCategories(id, categoryIds).getCategories().stream().map(IProductOutMapper::toCategoryDTO));
    }
}
