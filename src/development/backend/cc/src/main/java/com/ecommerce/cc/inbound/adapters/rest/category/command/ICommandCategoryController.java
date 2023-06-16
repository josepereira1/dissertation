package com.ecommerce.cc.inbound.adapters.rest.category.command;

import com.ecommerce.cc.inbound.adapters.rest.category.mappers.in.dtos.CategoryNameDTO;
import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Set;

@RequestMapping(path = "/api/categories")
public interface ICommandCategoryController {

    @PostMapping
    ResponseEntity createCategory(@Valid @NotNull @RequestBody CategoryNameDTO category) throws IOException, EcommerceBusinessLogicException;

    @RequestMapping(path = "/{id}/subcategories", method = {RequestMethod.PATCH, RequestMethod.POST})
    ResponseEntity updateSubcategories(@Valid @NotNull @Min(value = 0) @PathVariable(name = "id") Long parentCategoryId, @Valid @NotNull @RequestBody Set<String> subcategories) throws IOException, EcommerceBusinessLogicException;

    @PatchMapping(path = "/{id}/name")
    ResponseEntity updateName(@Valid @NotNull @Min(value = 0) @PathVariable(name = "id") Long parentCategoryId, @Valid @NotNull @RequestBody CategoryNameDTO subcategory) throws EcommerceBusinessLogicException, IOException;

    @DeleteMapping(path = "/{id}")
    ResponseEntity deleteCategory(@Valid @NotNull @Min(value = 0) @PathVariable(name = "id") Long id) throws EcommerceBusinessLogicException;

}
