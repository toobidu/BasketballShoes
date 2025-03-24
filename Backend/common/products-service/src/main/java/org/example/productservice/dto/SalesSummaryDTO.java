package org.example.productservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    LocalDate periodDate;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
