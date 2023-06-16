package com.ecommerce.qp.inbound.messaging.cqrs.product.create;

import com.ecommerce.qp.core.business.exceptions.product.ProductWithIdAlreadyExists;
import com.ecommerce.qp.core.business.logs.ILogs;
import com.ecommerce.qp.core.business.messaging.logic.IMessagingBusiness;
import com.ecommerce.qp.core.business.messaging.resources.IMessage;
import com.ecommerce.qp.core.business.resources.CounterMeasure;
import com.ecommerce.qp.core.business.resources.Version;
import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.ports.in.product.ICommandProduct;
import com.ecommerce.qp.core.ports.in.version.ICommandVersion;
import com.ecommerce.qp.inbound.messaging.cqrs.product.create.mappers.in.dtos.CreateProductFeatureProductDTO;
import com.ecommerce.qp.inbound.messaging.cqrs.product.create.mappers.in.IProductInMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSCreateProduct {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandProduct commandProduct;

    @Autowired
    private ICommandVersion commandVersion;

    @Autowired
    private IMessagingBusiness messagingBusiness;

    @Autowired
    private ILogs logs;

    public void cqrsCreateProduct(IMessage message){
        logs.logInfo("[CQRS] - Synchronization by create product command on cp service - message: " + message);
        Product product = null;
        try {
            product = IProductInMapper.toProduct(gson.fromJson(message.getData(), CreateProductFeatureProductDTO.class));
            if(product.getCountermeasure().equals(CounterMeasure.UNLOCKED))
                commandProduct.create(product);
            Version version = messagingBusiness.getGson().fromJson(message.getMetadata(), Version.class);
            commandVersion.createVersion(version);
        } catch (ProductWithIdAlreadyExists e) {
            e.printStackTrace();
            logs.logError("[CQRS] - Product already exists - product: " + product);
            //  TODO Log this
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[CQRS] - Internal server error - product: " + product);
            //  TODO Llogsog this
        }
    }
}
