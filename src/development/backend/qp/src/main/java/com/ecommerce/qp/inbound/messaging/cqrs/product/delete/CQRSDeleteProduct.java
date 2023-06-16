package com.ecommerce.qp.inbound.messaging.cqrs.product.delete;

import com.ecommerce.qp.core.business.exceptions.product.ProductNotExistException;
import com.ecommerce.qp.core.business.exceptions.product.ProductWithIdAlreadyExists;
import com.ecommerce.qp.core.business.logs.ILogs;
import com.ecommerce.qp.core.business.messaging.resources.IMessage;
import com.ecommerce.qp.core.business.resources.CounterMeasure;
import com.ecommerce.qp.core.business.resources.Version;
import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.ports.in.product.ICommandProduct;
import com.ecommerce.qp.inbound.messaging.cqrs.product.delete.mappers.in.IProductInMapper;
import com.ecommerce.qp.inbound.messaging.cqrs.product.delete.mappers.in.dtos.DeleteProductFeatureProductDTO;
import com.ecommerce.qp.inbound.messaging.version.IUtilsVersion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSDeleteProduct {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandProduct commandProduct;

    @Autowired
    private IUtilsVersion utilsVersion;

    @Autowired
    private ILogs logs;

    public void cqrsDeleteProduct(IMessage message){
        logs.logInfo("[CQRS] - Synchronization by delete product command on cp service - message: " + message);
        Product product = null;
        try {
            Version version = utilsVersion.isNextVersion(message);
            product = IProductInMapper.toProduct(gson.fromJson(message.getData(), DeleteProductFeatureProductDTO.class));
            commandProduct.delete(product.getId());
            utilsVersion.nextVersion(version);
        } catch (ProductWithIdAlreadyExists e) {
            e.printStackTrace();
            logs.logError("[CQRS] - Product already exists - product: " + product);
            //  TODO Log this
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[CQRS] - Internal server error - product: " + product);
            //  TODO Log this
        }
    }
}
