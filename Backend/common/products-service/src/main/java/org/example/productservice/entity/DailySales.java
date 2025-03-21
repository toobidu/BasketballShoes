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
@Table(name = "daily_sales")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DailySales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer saleId;

    @Column(name = "product_id", nullable = false)
    Integer productId;

    //Tổng số lượng sản phẩm đã bán
    @Column(name = "total_quantity_sold", nullable = false)
    Integer totalQuantitySold = 0;

    //Tổng doanh thu
    @Column(name = "total_revenue", nullable = false, precision = 15, scale = 2)
    BigDecimal totalRevenue = BigDecimal.ZERO;

    @Column(name = "sale_date", nullable = false)
    LocalDate saleDate;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt = LocalDateTime.now();
}
