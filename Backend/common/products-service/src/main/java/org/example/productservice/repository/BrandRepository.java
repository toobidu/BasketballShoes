package org.example.productservice.repository;

import org.example.productservice.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query("SELECT b FROM Brand b WHERE (:keyword IS NULL OR LOWER(b.brandName) LIKE %:keyword% OR LOWER(b.brandDescription) LIKE %:keyword%)")
    Page<Brand> findAllWithKeyword(String keyword, Pageable pageable);

    boolean existsByBrandName(String brandName);
}