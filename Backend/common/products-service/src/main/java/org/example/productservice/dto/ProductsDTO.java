package org.example.productservice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class ProductsDTO {

    Integer productId;
    String productName;
    Integer brandId;
    BigDecimal price;
    Integer stock;
    Integer launchYear;
    String nbaPlayer;
    String type;
    String productsDescription;
    Boolean isAvailable;
    BigDecimal averageRating;
    Integer ratingCount;
    BigDecimal size;

}
