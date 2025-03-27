package org.example.productservice.repository;

import org.example.productservice.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT c FROM Category c WHERE (:keyword IS NULL OR LOWER(c.categoryName) LIKE %:keyword% OR LOWER(c.categoryDescription) LIKE %:keyword%)")
    Page<Category> findAllWithKeyword(String keyword, Pageable pageable);

    boolean existsByCategoryName(String categoryName);
}