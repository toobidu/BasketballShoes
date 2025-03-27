package org.example.productservice.repository;

import org.example.productservice.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByProduct_ProductId(Integer productId, Pageable pageable);
    
    Page<Review> findByUserId(Integer userId, Pageable pageable);
    
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.productId = :productId")
    Double calculateAverageRating(Integer productId);
    
    Long countByProduct_ProductId(Integer productId);
}