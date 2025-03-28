package org.example.productservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sales_summary")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalesSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer salesSummaryId;

    @Column(name = "product_id", nullable = false)
    Integer productId;

    @Column(name = "period_type", nullable = false)
    String periodType;

    @Column(name = "total_quantity_sold", nullable = false)
    Integer totalQuantitySold = 0;

    @Column(name = "total_revenue", nullable = false, precision = 15, scale = 2)
    BigDecimal totalRevenue = BigDecimal.ZERO;

    @Column(name = "period_date", nullable = false)
    LocalDate periodDate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
}
