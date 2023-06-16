package com.ecommerce.cp.core.business.saga.sagas.createproduct.features;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.logic.product.command.CommandProduct;
import com.ecommerce.cp.core.business.logs.ILogs;
import com.ecommerce.cp.core.business.messaging.resources.IMessage;
import com.ecommerce.cp.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cp.core.business.resources.CounterMeasure;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.business.saga.sagas.createproduct.mappers.in.dtos.ProductDTO;
import com.ecommerce.cp.core.business.saga.sagas.createproduct.mappers.in.ICreateProductSagaInMapper;
import com.ecommerce.cp.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.cp.core.business.saga.sagas.utils.CommonSaga;
import com.ecommerce.cp.core.business.saga.sagas.utils.resources.SagaStatus;
import com.ecommerce.cp.core.ports.out.messaging.saga.ISagaOrchestrator;
import com.ecommerce.cp.core.ports.out.repository.createproductsaga.ICommandCreateProductSagaRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateProductSagaLocalTransaction {

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandCreateProductSagaRepository commandCreateProductSagaRepository;

    @Autowired
    private CommandProduct commandProduct;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    @Autowired
    private ILogs logs;

    public void callLocalTransaction(IMessage message) {
        try {
            localTransaction(message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.ERROR,500, e.getMessage() != null ? e.getMessage() : "Internal server error.", message.getMetadata(), message.getData());
            return;
        }
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS,200, "", message.getMetadata(), message.getData());
    }

    @Transactional(rollbackFor = Exception.class)
    public void localTransaction(IMessage message) throws EcommerceBusinessLogicException {
        CreateProductSaga createProductSaga;
        String sagaId = null;
        Product product = null;
        try {
            JsonElement data = message.getData();
            sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
            ProductDTO productDTO = gson.fromJson(data, ProductDTO.class);
            createProductSaga = new CreateProductSaga();
            createProductSaga.setSagaId(sagaId);
            product = ICreateProductSagaInMapper.toProduct(productDTO);
            product.setOwner(sagaId);
            product.setCountermeasure(CounterMeasure.BLOCKED); //  BLOCKED
            product = commandProduct.createProduct(product);
            fillMessageWithProduct(data, product);
            if (product.getStockStatus() != null)
                data.getAsJsonObject().addProperty("stock", product.getStockStatus().toString());
            if (product.getId() != null) createProductSaga.setProductId(product.getId());
            else {
                logs.logError("Product id not exists.");
                //  TODO Log this
                throw new NullPointerException();
            }
            createProductSaga.setSagaStatus(SagaStatus.PENDING);
            commandCreateProductSagaRepository.save(createProductSaga);
        }catch (Exception e){
            createProductSaga = new CreateProductSaga();
            if(sagaId != null) createProductSaga.setSagaId(sagaId);
            if (product.getId() != null) createProductSaga.setProductId(product.getId());
            createProductSaga.setSagaStatus(SagaStatus.ERROR);
            commandCreateProductSagaRepository.save(createProductSaga);
            throw e;
        }
    }

    private void fillMessageWithProduct(JsonElement data, Product product){
        data.getAsJsonObject().addProperty("id", product.getId());  //  synchronize product id with other services
        data.getAsJsonObject().addProperty("name", product.getName());
        data.getAsJsonObject().addProperty("amountInDiscount", product.getAmountInDiscount());
        data.getAsJsonObject().addProperty("finalPrice", product.getFinalPrice());
        data.getAsJsonObject().addProperty("sku", product.getSku());
        data.getAsJsonObject().addProperty("ean", product.getEan());
        data.getAsJsonObject().addProperty("pn", product.getPn());
    }
}
