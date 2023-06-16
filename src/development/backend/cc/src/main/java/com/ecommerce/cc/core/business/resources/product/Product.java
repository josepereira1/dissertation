package com.ecommerce.cc.core.business.resources.product;

import com.ecommerce.cc.core.business.resources.CounterMeasure;
import com.ecommerce.cc.core.business.resources.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product implements Comparable {

    @Id
    private String id;
    private String name;

    @Column(columnDefinition = "TEXT")
    //@Column(length = 1024)
    private String shortDetails;
    private String currency;
    private Double discountPercentage;
    private Double amountInDiscount;
    private Double finalPrice;
    private StockStatus stockStatus;

    @Column(columnDefinition = "TEXT")
    //@Column(length = 1024)
    private String links;
    private Visibility visibility;
    private String pn;              //  PN  (brand id)

    private String owner;
    private CounterMeasure countermeasure;

    //  fecth EAGER foi colocado, para resolver um erro no update product local transaction, antes não tinha qualquer tipo de fetch

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_product",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private Set<Category> categories;

    @Override
    public int compareTo(Object o) {
        Product product = (Product) o;
        if(this.getName().equals(product.getName())) return 1;
        else if(this.getName().equals(product.getName())) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shortDetails='" + shortDetails + '\'' +
                ", currency='" + currency + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", amountInDiscount=" + amountInDiscount +
                ", finalPrice=" + finalPrice +
                ", stockStatus=" + stockStatus +
                ", links='" + links + '\'' +
                ", visibility=" + visibility +
                ", pn='" + pn + '\'' +
                ", countermeasure=" + countermeasure +
                '}';
    }
}
