package org.example.productservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@Entity
@Table(name = "product_categories")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductCategoriesId.class)
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ProductCategories {

    @Id
    @Column(name = "product_id")
    Integer productId;

    @Id
    @Column(name = "category_id")
    Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    Products product;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    Categories category;

}

