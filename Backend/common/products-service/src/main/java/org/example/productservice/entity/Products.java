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
import java.time.LocalDateTime;
import java.util.Set;


@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer productId;

    @Column(name = "name", nullable = false, length = 100)
    String productName;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false, referencedColumnName = "id")
    Brands brand;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    BigDecimal price = BigDecimal.ZERO;

    @Column(name = "stock", nullable = false)
    Integer stock;

    @Column(name = "launch_year")
    Integer launchYear;

    @Column(name = "nba_player")
    String nbaPlayer;

    @Column(name = "type", nullable = false, length = 7)
    String type; //indoor/outdoor

    @Column(name = "description", columnDefinition = "TEXT")
    String productsDescription;

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
    LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductCategories> productCategories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Reviews> reviews;
}
