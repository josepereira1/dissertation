package com.ecommerce.inventory.inbound.rest.product.mappers.out;

import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.inbound.rest.product.mappers.out.dtos.ProductDTO;

public interface IProductOutMapper {
    static ProductDTO toProductDTO(Product product){
        ProductDTO tmp1 = new ProductDTO();
        tmp1.setId(product.getId());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        tmp1.setStockUnit(product.getStockUnit());
        tmp1.setStockQuantity(product.getStockQuantity());
        tmp1.setStockStatus(product.getStockStatus());
        return tmp1;
    }
}
