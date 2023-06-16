package com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.createproductincategories;

import com.ecommerce.qcvp.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcvp.core.business.exceptions.category.ProductAlreadyExistsException;
import com.ecommerce.qcvp.core.business.logs.ILogs;
import com.ecommerce.qcvp.core.business.messaging.resources.IMessage;
import com.ecommerce.qcvp.core.business.resources.CounterMeasure;
import com.ecommerce.qcvp.core.business.resources.Version;
import com.ecommerce.qcvp.core.business.resources.category.category.Visibility;
import com.ecommerce.qcvp.core.ports.in.category.ICommandCategory;
import com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.createproductincategories.mapper.in.IProductInMapper;
import com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.createproductincategories.mapper.in.dtos.CreateProductInCategoriesDTO;
import com.ecommerce.qcvp.inbound.messaging.version.IUtilsVersion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CQRSCreateProductInCategories {

    @Autowired
    private Gson gson = new GsonBuilder()
            //.registerTypeAdapter(CreateProductInCategoriesDTO.class, new CreateProductInCategoriesDTODeserializer())
            .create();

    @Autowired
    private ICommandCategory commandCategory;

    @Autowired
    private IUtilsVersion utilsVersion;

    @Autowired
    private ILogs logs;

    public void cqrsCreateProductInCategories(IMessage message){
        logs.logInfo("[CQRS] - Synchronization by create products in categories command on cc service - message: " + message);
        try {
            Version version = utilsVersion.isNextVersion(message);
            CreateProductInCategoriesDTO productAndCategories = gson.fromJson(message.getData(), CreateProductInCategoriesDTO.class);
            if(productAndCategories.getVisibility() == null && productAndCategories.getVisibility().equals(Visibility.NOT_VISIBLE)){
                utilsVersion.nextVersion(version);
                return;
            }
            Collection<Long> categoryIds = productAndCategories.getCategories();
            if(productAndCategories.getCountermeasure().equals(CounterMeasure.UNLOCKED))
                commandCategory.createProductsInCategories(IProductInMapper.toProduct(productAndCategories), categoryIds);
            utilsVersion.nextVersion(version);
        } catch (CategoryNotExistsException e){
            e.printStackTrace();
            logs.logError("[CQRS] - " + e.getMessage() + " - message: " + message);
        } catch (ProductAlreadyExistsException e) {
            e.printStackTrace();
            logs.logError("[CQRS] - " + e.getMessage() + " - message: " + message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[CQRS] - Internal server error: " + e.getMessage() + " - message: " + message);
        }
    }
}
