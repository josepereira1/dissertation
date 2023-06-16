package com.ecommerce.qct.inbound.messaging.cqrs.category.tree.updatecategorytree.mapper.in;

import com.ecommerce.qct.core.business.resources.category.tree.CategoryTree;
import com.ecommerce.qct.inbound.messaging.cqrs.category.tree.updatecategorytree.mapper.in.dtos.UpdateCategoryTreeDTO;

import java.util.stream.Collectors;

public interface ICategoryTreeInMapper {

    static CategoryTree toCategoryTree(UpdateCategoryTreeDTO categoryTree){
        CategoryTree tmp = new CategoryTree();
        tmp.setId(categoryTree.getId());
        tmp.setName(categoryTree.getName());
        tmp.setChildCategories(categoryTree.getChildCategories().stream().map(ICategoryTreeInMapper::toCategoryTree).collect(Collectors.toList()));
        return tmp;
    }
}
