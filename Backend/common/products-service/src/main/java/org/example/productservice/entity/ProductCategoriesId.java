package org.example.productservice.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCategoriesId implements java.io.Serializable {
    Integer productId;
    Integer categoryId;
}
