package com.ecommerce.qcvp.inbound.rest.category.query;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcvp.inbound.rest.category.exceptions.PageAndProductsPerPageParametersMustBeGreaterThanZeroException;
import com.ecommerce.qcvp.core.business.resources.category.category.Visibility;
import com.ecommerce.qcvp.core.ports.in.category.IQueryCategory;
import com.ecommerce.qcvp.inbound.rest.category.exceptions.VisibilityParameterIsInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
public class QueryCategoryController implements IQueryCategoryController{

    @Autowired
    private IQueryCategory queryCategory;

    @Override
    public ResponseEntity readCategoryProducts(Long id, Integer page,  Integer productsPerPage) throws EcommerceBusinessLogicException {
        if (page <= 0 || productsPerPage <= 0) throw PageAndProductsPerPageParametersMustBeGreaterThanZeroException.builder().build();
        return ResponseEntity.ok(queryCategory.readCategoryProducts(id, page, productsPerPage));
    }
}
