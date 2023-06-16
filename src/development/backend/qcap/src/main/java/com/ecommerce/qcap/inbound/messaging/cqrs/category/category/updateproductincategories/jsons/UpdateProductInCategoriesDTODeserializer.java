package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.updateproductincategories.jsons;
/*
import com.ecommerce.qc.inbound.messaging.cqrs.category.category.updateproductincategories.mapper.in.dtos.UpdateProductInCategoriesDTO;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;

public class UpdateProductInCategoriesDTODeserializer implements JsonDeserializer<UpdateProductInCategoriesDTO> {
    @Override
    public UpdateProductInCategoriesDTO deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        UpdateProductInCategoriesDTO productAndCategoryIds = new UpdateProductInCategoriesDTO();
        JsonObject jo = jsonElement.getAsJsonObject();
        if(jo.has("id")) productAndCategoryIds.setId(jo.get("id").getAsString());
        if(jo.has("name")) productAndCategoryIds.setName(jo.get("name").getAsString());
        if(jo.has("shortDetails")) productAndCategoryIds.setShortDetails(jo.get("shortDetails").getAsString());
        if(jo.has("currency")) productAndCategoryIds.setCurrency(jo.get("currency").getAsString());
        if(jo.has("discountPercentage")) productAndCategoryIds.setDiscountPercentage(jo.get("discountPercentage").getAsDouble());
        if(jo.has("amountInDiscount")) productAndCategoryIds.setAmountInDiscount(jo.get("amountInDiscount").getAsDouble());
        if(jo.has("finalPrice")) productAndCategoryIds.setFinalPrice(jo.get("finalPrice").getAsDouble());
        if(jo.has("links")) productAndCategoryIds.setLinks(jo.get("links").getAsString());
        if(jo.has("pn")) productAndCategoryIds.setPn(jo.get("pn").getAsString());
        if(jo.has("currentProductCategories")){
            JsonArray categoriesArr = jo.getAsJsonArray("currentProductCategories");
            Iterator<JsonElement> it = categoriesArr.iterator();
            HashSet<Long> categories = new HashSet<>();
            while (it.hasNext()) categories.add(it.next().getAsJsonObject().getAsLong());
            productAndCategoryIds.setCurrentProductCategories(categories);
        }
        if(jo.has("addCategories")){
            JsonArray categoriesArr = jo.getAsJsonArray("addCategories");
            Iterator<JsonElement> it = categoriesArr.iterator();
            HashSet<Long> categories = new HashSet<>();
            while (it.hasNext()) categories.add(it.next().getAsJsonObject().getAsLong());
            productAndCategoryIds.setAddCategories(categories);
        }
        if(jo.has("removeCategories")){
            JsonArray categoriesArr = jo.getAsJsonArray("removeCategories");
            Iterator<JsonElement> it = categoriesArr.iterator();
            HashSet<Long> categories = new HashSet<>();
            while (it.hasNext()) categories.add(it.next().getAsJsonObject().getAsLong());
            productAndCategoryIds.setRemoveCategories(categories);
        }
        if(jo.has("countermeasure")) productAndCategoryIds.setCountermeasure(jo.get("countermeasure").getAsString().charAt(0));
        return productAndCategoryIds;
    }
}
*/