package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.deleteproductincategories.jsons;
/*
import com.ecommerce.qc.inbound.messaging.cqrs.category.category.deleteproductincategories.mapper.in.dtos.DeleteProductInCategoriesDTO;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;

public class DeleteProductInCategoriesDTODeserializer implements JsonDeserializer<DeleteProductInCategoriesDTO> {
    @Override
    public DeleteProductInCategoriesDTO deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        DeleteProductInCategoriesDTO productAndCategoryIds = new DeleteProductInCategoriesDTO();
        JsonObject jo = jsonElement.getAsJsonObject();
        if(jo.has("id")) productAndCategoryIds.setId(jo.get("id").getAsString());
        if(jo.has("categoryIds")){
            JsonArray categoriesArr = jo.getAsJsonArray("categories");
            Iterator<JsonElement> it = categoriesArr.iterator();
            HashSet<Long> categories = new HashSet<>();
            while (it.hasNext()) categories.add(it.next().getAsJsonObject().getAsLong());
            productAndCategoryIds.setCategoryIds(categories);
        }
        return productAndCategoryIds;
    }
}
*/