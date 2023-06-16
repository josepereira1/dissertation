package com.ecommerce.qp.core.business.saga.sagas.createorder.features;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qp.core.business.exceptions.product.ProductNotExistException;
import com.ecommerce.qp.core.business.logic.product.query.QueryProduct;
import com.ecommerce.qp.core.business.logs.ILogs;
import com.ecommerce.qp.core.business.messaging.resources.IMessage;
import com.ecommerce.qp.core.business.messaging.resources.MessageStatus;
import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.business.saga.sagas.createorder.mappers.in.dtos.ProductDTO;
import com.ecommerce.qp.core.ports.out.saga.ISagaOrchestrator;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class CreateOrderSagaLocalTransaction {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private QueryProduct queryProduct;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    @Autowired
    private ILogs logs;

    public void callLocalTransaction(IMessage message){
        try{
            localTransaction(message);
        } catch (ProductNotExistException e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,404, e.getMessage() != null ? e.getMessage() : "Some products not exists.", message.getMetadata(), message.getData());
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,500, e.getMessage() != null ? e.getMessage() : "Internal server error.", message.getMetadata(), message.getData());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void localTransaction(IMessage message) throws EcommerceBusinessLogicException {
        ProductDTO[] productDTO;
        JsonObject data = message.getData().getAsJsonObject();
        if(data.has("products"))
            productDTO = gson.fromJson(data.get("products"), ProductDTO[].class);
        else throw new JsonParseException("products tag in fault in json");
        List<String> productIds = new ArrayList<>();
        Map<String, ProductDTO> productsAndQuantities = new HashMap<>();
        for(ProductDTO elem : productDTO) {
            productsAndQuantities.put(elem.getId(), elem);
            productIds.add(elem.getId());
        }
        Collection<Product> products = queryProduct.readProductsByIds(productIds);
        for(Product elem : products) {
            ProductDTO productAndQuantity = productsAndQuantities.get(elem.getId());
            productAndQuantity = updateProductDTO(elem, productAndQuantity);
            productsAndQuantities.put(productAndQuantity.getId(), productAndQuantity);
        }
        data.getAsJsonObject().add("products", gson.toJsonTree(productsAndQuantities.values()));
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS, 200, "", message.getMetadata(), data);
    }

    private ProductDTO updateProductDTO(Product sourceProduct, ProductDTO destinyProduct){
        destinyProduct.setId(sourceProduct.getId());
        destinyProduct.setName(sourceProduct.getName());
        destinyProduct.setCurrency(sourceProduct.getCurrency());
        destinyProduct.setInitialPrice(sourceProduct.getInitialPrice());
        destinyProduct.setDiscountPercentage(sourceProduct.getDiscountPercentage());
        destinyProduct.setAmountInDiscount(sourceProduct.getAmountInDiscount());
        destinyProduct.setVatPercentage(sourceProduct.getVatPercentage());
        destinyProduct.setAmountInVat(sourceProduct.getAmountInVat());
        destinyProduct.setFinalPrice(sourceProduct.getFinalPrice());
        destinyProduct.setShipping(sourceProduct.getShipping());
        destinyProduct.setSku(sourceProduct.getSku());
        destinyProduct.setEan(sourceProduct.getEan());
        destinyProduct.setPn(sourceProduct.getPn());
        return destinyProduct;
    }
}
