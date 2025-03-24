package org.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
    Integer productId;
    String productName;
    String description;
    BigDecimal price;
    Integer stock;
    Integer brandId;
    Integer launchYear;
    String nbaPlayer;
    String type;
    Boolean isAvailable;
    BigDecimal averageRating;
    Integer ratingCount;
    BigDecimal size;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
