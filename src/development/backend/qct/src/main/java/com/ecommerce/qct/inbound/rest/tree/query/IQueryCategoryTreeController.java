package com.ecommerce.qct.inbound.rest.tree.query;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/categories/tree")
public interface IQueryCategoryTreeController {

    @GetMapping
    ResponseEntity readCategoryTree() throws EcommerceBusinessLogicException;
}
