package com.ecommerce.qct.inbound.messaging;

import com.ecommerce.qct.core.business.logs.ILogs;
import com.ecommerce.qct.core.business.messaging.resources.IMessage;
import com.ecommerce.qct.inbound.messaging.cqrs.category.tree.CQRSCategoryTree;
import com.ecommerce.qct.inbound.messaging.messaging.consumer.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodSelection extends MessageHandler {

    @Autowired
    private CQRSCategoryTree cqrsCategoryTree;

    @Autowired
    private ILogs logs;

    @Override
    public void callMethod(String method, IMessage message) throws Exception {
        switch (method){
            case CQRSCategoryTree.CQRS_UPDATE_CATEGORY_TREE:
                cqrsCategoryTree.cqrsUpdateCategoryTree(message);
                break;
            default:
                logs.logError("Message not match any method - message: " + message);
                return;
        }
    }
}
