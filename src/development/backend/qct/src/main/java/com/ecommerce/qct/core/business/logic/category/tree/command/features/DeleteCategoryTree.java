package com.ecommerce.qct.core.business.logic.category.tree.command.features;

import com.ecommerce.qct.core.ports.out.repository.category.tree.ICommandCategoryTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteCategoryTree {

    @Autowired
    private ICommandCategoryTreeRepository commandCategoryTreeRepository;

    @Transactional(rollbackFor = Exception.class)
    public void deleteCategoryTree(){
        commandCategoryTreeRepository.deleteCategoryTreeByName("#root");
    }
}
