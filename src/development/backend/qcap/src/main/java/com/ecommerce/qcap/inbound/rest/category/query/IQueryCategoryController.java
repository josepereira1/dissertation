package com.ecommerce.qcap.inbound.rest.category.query;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@CrossOrigin
@RequestMapping(path = "/api/categories")
public interface IQueryCategoryController {

    @GetMapping(path = "/{id}/products")
    ResponseEntity readCategoryProductsByVisibility(@Valid @NotNull @PathVariable(name = "id") Long id, @Valid @NotNull @Min (value = 1) @RequestParam(name = "page") Integer page, @Valid @NotNull @Min(value = 1) @Max(value = 100) @RequestParam(name = "products_per_page") Integer productsPerPage, @Valid @NotBlank @RequestParam(name = "visibility") String visibility) throws EcommerceBusinessLogicException;
}
