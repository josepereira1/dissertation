package com.ecommerce.sc.core.business.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ShoppingCartAndProduct implements Comparable, Serializable {

    @JsonIgnore
    @EmbeddedId
    private ShoppingCartAndProductId id;

    private Double quantity;

    @JsonIgnore
    @ManyToOne(cascade = {})
    @JoinColumn(name = "fk_shopping_cart_id", referencedColumnName ="id", nullable = false)
    private ShoppingCart shoppingCart;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "fk_product_id", referencedColumnName ="id", nullable = false)
    private Product product;

    @Override
    public String toString() {
        return "ShoppingCartAndProduct{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        ShoppingCartAndProduct shoppingCartAndProduct = (ShoppingCartAndProduct) o;
        return this.getId().hashCode() == shoppingCartAndProduct.getId().hashCode();
    }

    @Override
    public int compareTo(Object o) {
        if(this.equals(o)) return 0;
        else return -1;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : -1;
    }
}
