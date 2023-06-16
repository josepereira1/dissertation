package com.ecommerce.qct.inbound.messaging.cqrs.category.tree;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qct.core.business.logs.ILogs;
import com.ecommerce.qct.core.business.messaging.resources.IMessage;
import com.ecommerce.qct.core.business.resources.Version;
import com.ecommerce.qct.inbound.messaging.cqrs.category.tree.updatecategorytree.CQRSUpdateCategoryTree;
import com.ecommerce.qct.inbound.messaging.version.IUtilsVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSCategoryTree {

    public static final String CQRS_UPDATE_CATEGORY_TREE = "cqrs.0.update.category.tree";

    @Autowired
    private CQRSUpdateCategoryTree cqrsUpdateCategoryTree;

    @Autowired
    private IUtilsVersion utilsVersion;

    @Autowired
    private ILogs logs;

    public void cqrsUpdateCategoryTree(IMessage message){
        try {
            Version version = utilsVersion.isNextVersion(message);
            cqrsUpdateCategoryTree.cqrsUpdateCategoryTree(message);
            utilsVersion.nextVersion(version);
        } catch (EcommerceBusinessLogicException e) {
            e.printStackTrace();
            logs.logError("[CQRS] - " + e.getMessage() + " - message: " + message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[CQRS] - " + e.getMessage() + " - message: " + message);
        }
    }
}
