package com.ecommerce.cc.core.business.logic.product.command.features;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.cc.core.business.logic.product.CommonProduct;
import com.ecommerce.cc.core.business.logic.version.command.CommandVersion;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSDeleteProductInCategories;
import com.ecommerce.cc.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.cc.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeleteProduct {

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private CommonProduct commonProduct;

    @Autowired
    private ICQRSDeleteProductInCategories cqrsDeleteProductInCategories;

    @Autowired
    private CommandVersion commandVersion;

    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(String id) throws EcommerceBusinessLogicException {
        Optional<Product> optional = queryProductRepository.findOneForUpdate(id);
        if(!optional.isPresent())
            throw ProductWithThatIdNotExistException.builder().id(id).build();
        Product product = optional.get();
        commonProduct.updateProductCategories(product.getCategories().stream().map(c -> c.getId()).collect(Collectors.toSet()), null,  product);
        commandProductRepository.deleteById(id);

        Collection<Long> currentCategories = CommonProduct.getCategoriesIds(product.getCategories());
        if((currentCategories != null && !currentCategories.isEmpty())) {
            Version categoriesVersion;
            for(Long categoryId : currentCategories){
                categoriesVersion = commandVersion.generateVersion("c" + categoryId);
                cqrsDeleteProductInCategories.synchronize(categoriesVersion, id, currentCategories);
            }
        }
    }
}
