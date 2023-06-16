package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.deleteproductincategories;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcap.core.business.logs.ILogs;
import com.ecommerce.qcap.core.business.messaging.resources.IMessage;
import com.ecommerce.qcap.core.business.resources.Version;
import com.ecommerce.qcap.core.ports.in.category.ICommandCategory;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.deleteproductincategories.mapper.in.dtos.DeleteProductInCategoriesDTO;
import com.ecommerce.qcap.inbound.messaging.version.IUtilsVersion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CQRSDeleteProductInCategories {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandCategory commandCategory;

    @Autowired
    private IUtilsVersion utilsVersion;

    @Autowired
    private ILogs logs;

    public void deleteProductInCategories(IMessage message){
        logs.logInfo("[CQRS] - Synchronization by delete products in categories command on cc service - message: " + message);
        try {
            Version version = utilsVersion.isNextVersion(message);
            DeleteProductInCategoriesDTO productIdAndCategories = gson.fromJson(message.getData(), DeleteProductInCategoriesDTO.class);
            Collection<Long> categories = productIdAndCategories.getCategoryIds();
            commandCategory.deleteProductsInCategories(productIdAndCategories.getId(), categories);
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
