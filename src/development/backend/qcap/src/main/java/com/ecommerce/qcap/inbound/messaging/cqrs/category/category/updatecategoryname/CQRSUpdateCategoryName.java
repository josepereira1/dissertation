package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.updatecategoryname;

import com.ecommerce.qcap.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcap.core.business.exceptions.version.VersionNotExistsException;
import com.ecommerce.qcap.core.business.logs.ILogs;
import com.ecommerce.qcap.core.business.messaging.resources.IMessage;
import com.ecommerce.qcap.core.business.resources.Version;
import com.ecommerce.qcap.core.ports.in.category.ICommandCategory;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.updatecategoryname.mapper.dtos.UpdateCategoryNameDTO;
import com.ecommerce.qcap.inbound.messaging.version.IUtilsVersion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSUpdateCategoryName {

    @Autowired
    private Gson gson = new GsonBuilder()
            //.registerTypeAdapter(UpdateCategoryNameDTO.class, new UpdateCategoryNameDTODeserializer())
            .create();

    @Autowired
    private ICommandCategory commandCategory;

    @Autowired
    private IUtilsVersion utilsVersion;

    @Autowired
    private ILogs logs;

    public void cqrsUpdateCategoryName(IMessage message){
        logs.logInfo("[CQRS] - Synchronization by create categories command on cc service - message: " + message);
        try {
            Version version = utilsVersion.isNextVersion(message);
            UpdateCategoryNameDTO category = gson.fromJson(message.getData(), UpdateCategoryNameDTO.class);
            commandCategory.updateCategoryName(category.getId(), category.getName());
            utilsVersion.nextVersion(version);
        } catch (CategoryNotExistsException e) {
            e.printStackTrace();
            logs.logError("[CQRS] - Category not exists - message: " + message);
        } catch (VersionNotExistsException e) {
            e.printStackTrace();
            logs.logError("[CQRS] - Version not exists - message: " + message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[CQRS] - Internal server error - message: " + message);
        }
    }
}
