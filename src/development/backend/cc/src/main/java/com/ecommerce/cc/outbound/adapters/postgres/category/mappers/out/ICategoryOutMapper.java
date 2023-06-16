package com.ecommerce.cc.outbound.adapters.postgres.category.mappers.out;

import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.outbound.adapters.postgres.category.mappers.out.projections.CategoryIdAndNameChildCategories;
import com.ecommerce.cc.outbound.adapters.postgres.category.mappers.out.projections.CategoryProducts;
import com.ecommerce.cc.outbound.adapters.postgres.category.mappers.out.projections.ProductIdAndNameAndPriceAndDiscountAndStockStatus;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
public interface ICategoryOutMapper {
    static Category toCategory(CategoryIdAndNameChildCategories category){
        Category tmp1 = new Category();
        tmp1.setId(category.getId());
        tmp1.setName(category.getName());
        HashSet<Category> categories = new HashSet<>();
        if(category.getChildCategories() != null) {
            for (CategoryIdAndNameChildCategories elem : category.getChildCategories())
                categories.add(toCategory(elem));
        }
        tmp1.setChildCategories(categories);
        return tmp1;
    }

    static Category toCategory(CategoryProducts categoryProducts){
        Category tmp1 = new Category();

        HashSet<Product> products = new HashSet<>();

        if(categoryProducts.getProducts() != null) {
            for (ProductIdAndNameAndPriceAndDiscountAndStockStatus elem : categoryProducts.getProducts()) {
                Product tmp2 = new Product();
                tmp2.setId(elem.getId());
                tmp2.setName(elem.getName());
                tmp2.setShortDetails(elem.getShortDetails());
                tmp2.setCurrency(elem.getCurrency());
                tmp2.setDiscountPercentage(elem.getDiscountPercentage());
                tmp2.setAmountInDiscount(elem.getAmountInDiscount());
                tmp2.setFinalPrice(elem.getFinalPrice());
                tmp2.setStockStatus(elem.getStockStatus());
                tmp2.setPn(elem.getPn());
                products.add(tmp2);
            }
        }
        tmp1.setProducts(products);
        return tmp1;
    }
}
