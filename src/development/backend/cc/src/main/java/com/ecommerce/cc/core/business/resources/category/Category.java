package com.ecommerce.cc.core.business.resources.category;

import com.ecommerce.cc.core.business.resources.CounterMeasure;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})  //  necessary to the delete method
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category implements Comparable, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private Set<Category> childCategories;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id", referencedColumnName = "id")
    private Category parentCategory;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;

    @JsonIgnore
    private String owner;
    @JsonIgnore
    private CounterMeasure countermeasure;

    @Override
    public int compareTo(Object o) {
        return this.id.compareTo(((Category) o).id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + this.name + '\'' +
                ", childCategories=" + childCategories +
                '}';
    }
}
