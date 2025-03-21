package org.example.productservice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class DailySalesDTO {

    Integer salesId;
    Integer productId;
    Integer totalQuantitySold;
    BigDecimal totalRevenue;
    LocalDate salesDate;
}
