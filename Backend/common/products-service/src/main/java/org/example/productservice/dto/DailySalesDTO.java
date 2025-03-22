package org.example.productservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailySalesDTO {
    Integer salesId;
    Integer productId;
    Integer totalQuantitySold;
    BigDecimal totalRevenue;
    LocalDate salesDate;
}
