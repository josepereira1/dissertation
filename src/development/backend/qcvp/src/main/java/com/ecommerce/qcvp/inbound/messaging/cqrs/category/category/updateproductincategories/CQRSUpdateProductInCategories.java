package com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.updateproductincategories;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcvp.core.business.logs.ILogs;
import com.ecommerce.qcvp.core.business.messaging.resources.IMessage;
import com.ecommerce.qcvp.core.business.resources.CounterMeasure;
import com.ecommerce.qcvp.core.business.resources.Version;
import com.ecommerce.qcvp.core.business.resources.category.category.Visibility;
import com.ecommerce.qcvp.core.ports.in.category.ICommandCategory;
import com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.updateproductincategories.mapper.in.IProductInMapper;
import com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.updateproductincategories.mapper.in.dtos.UpdateProductInCategoriesDTO;
import com.ecommerce.qcvp.inbound.messaging.version.IUtilsVersion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CQRSUpdateProductInCategories {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandCategory commandCategory;

    @Autowired
    private IUtilsVersion utilsVersion;

    @Autowired
    private ILogs logs;

    public void updateProductInCategories(IMessage message){
        logs.logInfo("[CQRS] - Synchronization by update products in categories command on cc service - message: " + message);
        try {
            Version version = utilsVersion.isNextVersion(message);
            UpdateProductInCategoriesDTO productAndAddCategoriesAndRemoveCategories = gson.fromJson(message.getData(), UpdateProductInCategoriesDTO.class);
            if(productAndAddCategoriesAndRemoveCategories.getVisibility() == null || productAndAddCategoriesAndRemoveCategories.getVisibility().equals(Visibility.NOT_VISIBLE)){
                utilsVersion.nextVersion(version);
                return;
            }
            Collection<Long> addCategoryIds = productAndAddCategoriesAndRemoveCategories.getAddCategories();
            Collection<Long> removeCategoryIds = productAndAddCategoriesAndRemoveCategories.getRemoveCategories();
            Collection<Long> currentProductCategories = productAndAddCategoriesAndRemoveCategories.getCurrentProductCategories();
            if(productAndAddCategoriesAndRemoveCategories.getCountermeasure().equals(CounterMeasure.UNLOCKED))
                commandCategory.updateProductInCategories(IProductInMapper.toProduct(productAndAddCategoriesAndRemoveCategories), currentProductCategories, addCategoryIds, removeCategoryIds);
            utilsVersion.nextVersion(version);
        }  catch (EcommerceBusinessLogicException e) {
            e.printStackTrace();
            logs.logError("[CQRS] - " + e.getMessage() + " - message: " + message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[CQRS] - Internal server error: " + e.getMessage() + " - message: " + message);
        }
    }
}
