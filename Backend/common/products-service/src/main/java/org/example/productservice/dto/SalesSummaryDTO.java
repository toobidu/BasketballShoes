package org.example.productservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesSummaryDTO {
    Integer salesSummaryId;
    Integer productId;
    Integer totalQuantitySold;
    BigDecimal totalRevenue;
    String periodType;
    LocalDate startDate;
}
