package com.ecommerce.sc.core.business.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class ShoppingCartAndProductId implements Serializable {

    @Column(name="shopping_cart_id")
    private String shoppingCartId;

    @Column(name="product_id")
    private String productId;

    @Override
    public int hashCode() {
        return (this.shoppingCartId + this.productId).hashCode();
    }
}
