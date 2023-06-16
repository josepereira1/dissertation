package com.ecommerce.qcvp.inbound.messaging;

import com.ecommerce.qcvp.core.business.logs.ILogs;
import com.ecommerce.qcvp.core.business.messaging.resources.IMessage;
import com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.CQRSCategory;
import com.ecommerce.qcvp.inbound.messaging.messaging.consumer.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodSelection extends MessageHandler {

    @Autowired
    private CQRSCategory cqrsCategory;

    @Autowired
    private ILogs logs;

    @Override
    public void callMethod(String method, IMessage message) throws Exception {
        switch (method){
            case CQRSCategory.CQRS_CREATE_CATEGORIES_METHOD_NAME:
                cqrsCategory.cqrsCreateCategories(message);
                break;
            case CQRSCategory.CQRS_CREATE_PRODUCT_IN_CATEGORIES_METHOD_NAME:
                cqrsCategory.cqrsCreateProductInCategories(message);
                break;
            case CQRSCategory.CQRS_DELETE_CATEGORIES_METHOD_NAME:
                cqrsCategory.cqrsDeleteCategories(message);
                break;
            case CQRSCategory.CQRS_DELETE_PRODUCT_IN_CATEGORIES_METHOD_NAME:
                cqrsCategory.deleteProductInCategories(message);
                break;
            case CQRSCategory.CQRS_UPDATE_CATEGORY_NAME_METHOD_NAME:
                cqrsCategory.cqrsUpdateCategoryName(message);
                break;
            case CQRSCategory.CQRS_UPDATE_PRODUCT_IN_CATEGORIES_NAME_METHOD_NAME:
                cqrsCategory.updateProductInCategories(message);
                break;
            default:
                logs.logError("Message not match any method - message: " + message);
                return;
        }
    }
}
