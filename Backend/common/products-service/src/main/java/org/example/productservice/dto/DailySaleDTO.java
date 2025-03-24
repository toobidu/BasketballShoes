package org.example.productservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailySaleDTO {
    Integer saleId;
    Integer productId;
    Integer totalQuantitySold;
    BigDecimal totalRevenue;
    LocalDate salesDate;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
