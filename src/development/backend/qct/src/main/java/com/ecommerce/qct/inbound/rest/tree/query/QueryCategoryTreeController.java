package com.ecommerce.qct.inbound.rest.tree.query;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qct.core.ports.in.tree.IQueryCategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryCategoryTreeController implements IQueryCategoryTreeController{

    @Autowired
    private IQueryCategoryTree queryCategoryTree;

    @CrossOrigin
    @Override
    public ResponseEntity readCategoryTree() throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(queryCategoryTree.readCategoryTree());
    }
}
