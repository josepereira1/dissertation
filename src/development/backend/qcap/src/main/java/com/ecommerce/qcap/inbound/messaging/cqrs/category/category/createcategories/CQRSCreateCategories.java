package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createcategories;

import com.ecommerce.qcap.core.business.exceptions.category.CategoryAlreadyExistsException;
import com.ecommerce.qcap.core.business.exceptions.category.RootCategoryAlreadyExistsException;
import com.ecommerce.qcap.core.business.logs.ILogs;
import com.ecommerce.qcap.core.business.messaging.resources.IMessage;
import com.ecommerce.qcap.core.business.resources.Version;
import com.ecommerce.qcap.core.business.resources.category.category.Category;
import com.ecommerce.qcap.core.ports.in.category.ICommandCategory;
import com.ecommerce.qcap.core.ports.in.version.ICommandVersion;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createcategories.mapper.in.ICategoryInMapper;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createcategories.mapper.in.dtos.CreateCategoriesDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSCreateCategories {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandCategory commandCategory;

    @Autowired
    private ICommandVersion commandVersion;

    @Autowired
    private ILogs logs;

    public void cqrsCreateCategories(IMessage message){
        logs.logInfo("[CQRS] - Synchronization by create categories command on cc service - message: " + message);
        try {
            Category category = commandCategory.createCategories(ICategoryInMapper.toCategory(gson.fromJson(message.getData(), CreateCategoriesDTO.class)));
            Version version = new Version();
            version.setGroupId("c" + category.getId());
            version.setDescription("Version of category with id " + category.getId());
            version.setValue(1);
            commandVersion.createVersion(version);
        } catch (RootCategoryAlreadyExistsException e){
            e.printStackTrace();
            logs.logError("[CQRS] - Root category already exists - message: " + message);
        } catch (CategoryAlreadyExistsException e) {
            e.printStackTrace();
            logs.logError("[CQRS] - Category already exists - message: " + message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[CQRS] - Internal server error - message: " + message);
        }
    }
}
