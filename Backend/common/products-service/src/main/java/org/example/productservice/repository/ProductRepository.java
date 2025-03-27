package org.example.productservice.repository;

import org.example.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE " +
           "(:keyword IS NULL OR LOWER(p.productName) LIKE %:keyword% OR LOWER(p.productsDescription) LIKE %:keyword%) AND " +
           "(:brandId IS NULL OR p.brand.brandId = :brandId) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
           "(:isAvailable IS NULL OR p.isAvailable = :isAvailable)")
    Page<Product> findAllWithFilters(String keyword, Integer brandId, BigDecimal minPrice, 
                                   BigDecimal maxPrice, Boolean isAvailable, Pageable pageable);

    List<Product> findByBrand_BrandId(Integer brandId);
    
    boolean existsByProductName(String productName);
}