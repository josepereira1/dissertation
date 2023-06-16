package com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.deletecategories;

import com.ecommerce.qcvp.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcvp.core.business.logs.ILogs;
import com.ecommerce.qcvp.core.business.messaging.resources.IMessage;
import com.ecommerce.qcvp.core.business.resources.Version;
import com.ecommerce.qcvp.core.ports.in.category.ICommandCategory;
import com.ecommerce.qcvp.inbound.messaging.version.IUtilsVersion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class CQRSDeleteCategories {
    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandCategory commandCategory;

    @Autowired
    private IUtilsVersion utilsVersion;

    @Autowired
    private ILogs logs;

    public void cqrsDeleteCategories(IMessage message){
        logs.logInfo("[CQRS] - Synchronization by delete categories command on cc service - message: " + message);
        try {
            Version version = utilsVersion.isNextVersion(message);
            commandCategory.deleteCategories(Arrays.asList(gson.fromJson(message.getData(), Long[].class)));
            utilsVersion.nextVersion(version);
        } catch (CategoryNotExistsException e) {
            e.printStackTrace();
            logs.logError("[CQRS] - Category not exists - message: " + message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[CQRS] - Internal server error - message: " + message);
        }
    }
}
