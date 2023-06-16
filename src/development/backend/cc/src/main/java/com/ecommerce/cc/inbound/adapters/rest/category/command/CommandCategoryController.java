package com.ecommerce.cc.inbound.adapters.rest.category.command;

import com.ecommerce.cc.core.business.exceptions.*;
import com.ecommerce.cc.inbound.adapters.rest.category.mappers.in.ICategoryInMapper;
import com.ecommerce.cc.inbound.adapters.rest.category.mappers.in.dtos.CategoryNameDTO;
import com.ecommerce.cc.core.ports.in.category.ICommandCategory;
import com.ecommerce.cc.inbound.adapters.rest.category.mappers.out.ICategoryOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class CommandCategoryController implements ICommandCategoryController {

    @Autowired
    private ICommandCategory commandCategoryInboundPort;

    @Override
    public ResponseEntity createCategory(CategoryNameDTO category) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(ICategoryOutMapper.toCategoryIdAndNameChildCategoriesDTO(commandCategoryInboundPort.createCategory(ICategoryInMapper.toCategory(category))));
    }

    @Override
    public ResponseEntity updateSubcategories(Long parentCategoryId, Set<String> subcategories) throws EcommerceBusinessLogicException, IOException {
        return ResponseEntity.ok(commandCategoryInboundPort.updateCategorySubcategories(parentCategoryId, subcategories.stream().map(ICategoryInMapper::toCategory).collect(Collectors.toList())).stream().map(ICategoryOutMapper::toCategoryIdAndNameChildCategoriesDTO));
    }

    @Override
    public ResponseEntity updateName(Long parentCategoryId, CategoryNameDTO subcategory) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(ICategoryOutMapper.toCategoryIdAndNameDTO(commandCategoryInboundPort.updateCategoryName(parentCategoryId, ICategoryInMapper.toCategory(subcategory))));
    }

    @Override
    public ResponseEntity deleteCategory(Long categoryId) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(ICategoryOutMapper.toCategoryIdAndNameChildCategoriesDTO(commandCategoryInboundPort.deleteCategory(categoryId)));
    }
}
