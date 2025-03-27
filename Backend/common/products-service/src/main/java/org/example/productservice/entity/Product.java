package org.example.productservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer productId;

    @NotBlank(message = "Tên sản phẩm không được để trống!")
    @Column(name = "name", nullable = false, length = 100)
    String productName;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false, referencedColumnName = "id")
    Brand brand;

    @Column(name = "price", nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    BigDecimal price;

    @Min(0)
    @Column(name = "stock", nullable = false)
    Integer stock;

    @Min(1900)
    @Max(2050)
    @Column(name = "launch_year")
    Integer launchYear;

    @Column(name = "nba_player")
    String nbaPlayer;

    @Column(name = "type", nullable = false, length = 7)
    String type;

    @Column(name = "description", columnDefinition = "TEXT")
    String productsDescription;

    @NotNull
    @Column(name = "is_available", nullable = false)
    Boolean isAvailable;

    @Column(name = "average_rating", nullable = false, precision = 3, scale = 2)
    BigDecimal averageRating = BigDecimal.ZERO;

    @Column(name = "rating_count", nullable = false)
    Integer ratingCount = 0;

    @Column(name = "size", nullable = false, precision = 4, scale = 1)
    BigDecimal size = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    Set<Review> reviews = new HashSet<>();
}

