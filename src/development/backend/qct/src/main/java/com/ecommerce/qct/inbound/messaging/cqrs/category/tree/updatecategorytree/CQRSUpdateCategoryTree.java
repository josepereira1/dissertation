package com.ecommerce.qct.inbound.messaging.cqrs.category.tree.updatecategorytree;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qct.core.business.logs.ILogs;
import com.ecommerce.qct.core.business.messaging.resources.IMessage;
import com.ecommerce.qct.core.ports.in.tree.ICommandCategoryTree;
import com.ecommerce.qct.inbound.messaging.cqrs.category.tree.updatecategorytree.mapper.in.ICategoryTreeInMapper;
import com.ecommerce.qct.inbound.messaging.cqrs.category.tree.updatecategorytree.mapper.in.dtos.UpdateCategoryTreeDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSUpdateCategoryTree {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandCategoryTree commandCategoryTree;

    @Autowired
    private ILogs logs;

    public void cqrsUpdateCategoryTree(IMessage message){
        logs.logInfo("[CQRS] - Synchronization by create categories command on cc service - message: " + message);
        try {
            commandCategoryTree.updateCategoryTree(ICategoryTreeInMapper.toCategoryTree(gson.fromJson(message.getData(), UpdateCategoryTreeDTO.class)));
        } catch (EcommerceBusinessLogicException e) {
            e.printStackTrace();
            logs.logError("[CQRS] - " + e.getMessage() +" - message: " + message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[CQRS] - Internal server error - message: " + message);
        }
    }
}
