package com.ecommerce.qcap.inbound.messaging.cqrs.category.category;

import com.ecommerce.qcap.core.business.messaging.resources.IMessage;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createcategories.CQRSCreateCategories;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createproductincategories.CQRSCreateProductInCategories;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.deletecategories.CQRSDeleteCategories;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.deleteproductincategories.CQRSDeleteProductInCategories;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.updatecategoryname.CQRSUpdateCategoryName;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.updateproductincategories.CQRSUpdateProductInCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSCategory {

    public static final String CQRS_CREATE_CATEGORIES_METHOD_NAME = "cqrs.0.create.categories";
    public static final String CQRS_CREATE_PRODUCT_IN_CATEGORIES_METHOD_NAME = "cqrs.0.create.product.in.categories";
    public static final String CQRS_DELETE_CATEGORIES_METHOD_NAME = "cqrs.0.delete.categories";
    public static final String CQRS_DELETE_PRODUCT_IN_CATEGORIES_METHOD_NAME = "cqrs.0.delete.product.in.categories";
    public static final String CQRS_UPDATE_CATEGORY_NAME_METHOD_NAME = "cqrs.0.update.category.name";
    public static final String CQRS_UPDATE_PRODUCT_IN_CATEGORIES_NAME_METHOD_NAME = "cqrs.0.update.product.in.categories";

    @Autowired
    private CQRSCreateCategories cqrsCreateCategories;

    @Autowired
    private CQRSCreateProductInCategories cqrsCreateProductInCategories;

    @Autowired
    private CQRSDeleteCategories cqrsDeleteCategories;

    @Autowired
    private CQRSDeleteProductInCategories cqrsDeleteProductInCategories;

    @Autowired
    private CQRSUpdateCategoryName cqrsUpdateCategoryName;

    @Autowired
    private CQRSUpdateProductInCategories cqrsUpdateProductInCategories;

    public void cqrsCreateCategories(IMessage message){
        cqrsCreateCategories.cqrsCreateCategories(message);
    }

    public void cqrsCreateProductInCategories(IMessage message){
        cqrsCreateProductInCategories.cqrsCreateProductInCategories(message);
    }

    public void cqrsDeleteCategories(IMessage message){
        cqrsDeleteCategories.cqrsDeleteCategories(message);
    }

    public void deleteProductInCategories(IMessage message){
        cqrsDeleteProductInCategories.deleteProductInCategories(message);
    }

    public void cqrsUpdateCategoryName(IMessage message){
        cqrsUpdateCategoryName.cqrsUpdateCategoryName(message);
    }

    public void updateProductInCategories(IMessage message){
        cqrsUpdateProductInCategories.updateProductInCategories(message);
    }
}
