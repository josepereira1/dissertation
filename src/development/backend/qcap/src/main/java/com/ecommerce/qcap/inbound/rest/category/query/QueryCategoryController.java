package com.ecommerce.qcap.inbound.rest.category.query;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcap.inbound.rest.category.exceptions.PageAndProductsPerPageParametersMustBeGreaterThanZeroException;
import com.ecommerce.qcap.core.business.resources.category.category.Visibility;
import com.ecommerce.qcap.core.ports.in.category.IQueryCategory;
import com.ecommerce.qcap.inbound.rest.category.exceptions.VisibilityParameterIsInvalidException;
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
    public ResponseEntity readCategoryProductsByVisibility(@Valid @NotNull Long id, @Valid @NotNull @Min(value = 1) Integer page, @Valid @NotNull @Min(value = 1) @Max(value = 100) Integer productsPerPage, @Valid String visibility) throws EcommerceBusinessLogicException {
        if (page <= 0 || productsPerPage <= 0)
            throw PageAndProductsPerPageParametersMustBeGreaterThanZeroException.builder().build();
        if(visibility.toUpperCase().equals(Visibility.VISIBLE.name()) || visibility.toUpperCase().equals(Visibility.NOT_VISIBLE.name()))
            return ResponseEntity.ok(queryCategory.readCategoryProductsByPaginationAndVisibility(id, page, productsPerPage, Visibility.valueOf(visibility.toUpperCase())));
        else if(visibility.toUpperCase().equals("ALL"))
            return ResponseEntity.ok(queryCategory.readCategoryProductsByPaginationAndVisibility(id, page, productsPerPage, null));
        else throw VisibilityParameterIsInvalidException.builder().build();
    }
}
