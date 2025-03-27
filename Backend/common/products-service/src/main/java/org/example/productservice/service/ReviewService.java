package org.example.productservice.service;

import org.example.productservice.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReviewService {
    Page<ReviewDTO> findByProductId(Integer productId, Pageable pageable);

    Page<ReviewDTO> findByUserId(Integer userId, Pageable pageable);

    Optional<ReviewDTO> findById(Integer id);

    ReviewDTO save(ReviewDTO reviewDTO);

    ReviewDTO update(Integer id, ReviewDTO reviewDTO);

    void deleteById(Integer id);

    Double getAverageRating(Integer productId);

    Long getReviewCount(Integer productId);
}