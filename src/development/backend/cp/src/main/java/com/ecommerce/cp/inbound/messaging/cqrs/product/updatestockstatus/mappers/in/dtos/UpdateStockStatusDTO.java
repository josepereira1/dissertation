package com.ecommerce.cp.inbound.messaging.cqrs.product.updatestockstatus.mappers.in.dtos;

import com.ecommerce.cp.core.business.resources.product.StockStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateStockStatusDTO {
    private String id;
    private StockStatus stockStatus;
}
