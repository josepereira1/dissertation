package com.ecommerce.qp.inbound.messaging.cqrs.product.create.jsons;
/*
import com.ecommerce.qp.core.business.resources.product.StockStatus;
import com.ecommerce.qp.core.business.resources.product.Visibility;
import com.ecommerce.qp.inbound1.messaging.cqrs.product.create.mappers.in.dtos.CreateProductFeatureProductDTO;
import com.google.gson.*;
import java.lang.reflect.Type;

public class CreateProductFeatureProductDTODeserializer implements JsonDeserializer<CreateProductFeatureProductDTO> {
    @Override
    public CreateProductFeatureProductDTO deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        CreateProductFeatureProductDTO product = new CreateProductFeatureProductDTO();
        JsonObject jo = jsonElement.getAsJsonObject();
        if(jo.has("id")) product.setId(jo.get("id").getAsString());
        if(jo.has("name")) product.setName(jo.get("name").getAsString());
        if(jo.has("shortDetails")) product.setLongDetails(jsonDeserializationContext.deserialize(jo.getAsJsonObject("shortDetails"), Object.class));
        if(jo.has("longDetails")) product.setLongDetails(jsonDeserializationContext.deserialize(jo.getAsJsonObject("longDetails"), Object.class));
        if(jo.has("currency")) product.setCurrency(jo.get("currency").getAsString());
        if(jo.has("initialPrice")) product.setInitialPrice(jo.get("initialPrice").getAsDouble());
        if(jo.has("discountPercentage")) product.setDiscountPercentage(jo.get("discountPercentage").getAsDouble());
        if(jo.has("amountInDiscount")) product.setAmountInDiscount(jo.get("amountInDiscount").getAsDouble());
        if(jo.has("vatPercentage")) product.setVatPercentage(jo.get("vatPercentage").getAsDouble());
        if(jo.has("amountInVat")) product.setAmountInVat(jo.get("amountInVat").getAsDouble());
        if(jo.has("finalPrice")) product.setFinalPrice(jo.get("finalPrice").getAsDouble());
        if(jo.has("shipping")) product.setShipping(jo.get("shipping").getAsDouble());
        if(jo.has("stockStatus")) product.setStockStatus(StockStatus.valueOf(jo.get("stockStatus").getAsString()));
        if(jo.has("links")) product.setLongDetails(jsonDeserializationContext.deserialize(jo.getAsJsonObject("links"), Object.class));
        if(jo.has("visibility")) product.setVisibility(Visibility.valueOf(jo.get("visibility").getAsString()));
        if(jo.has("sku")) product.setSku(jo.get("sku").getAsString());
        if(jo.has("ean")) product.setEan(jo.get("ean").getAsString());
        if(jo.has("pn")) product.setPn(jo.get("pn").getAsString());
        if(jo.has("countermeasure")) product.setCountermeasure(jo.get("countermeasure").getAsString().charAt(0));
        return product;
    }
}
*/