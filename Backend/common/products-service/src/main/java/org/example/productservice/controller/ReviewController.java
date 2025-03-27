package org.example.productservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.ReviewDTO;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import org.example.productservice.exception.BadRequestException;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<Map<String, Object>> getReviewsByProductId(
            @PathVariable Integer productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "reviewId") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<ReviewDTO> reviewPage = reviewService.findByProductId(productId, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviewPage.getContent());
        response.put("currentPage", reviewPage.getNumber());
        response.put("totalItems", reviewPage.getTotalElements());
        response.put("totalPages", reviewPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getReviewsByUserId(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "reviewId") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<ReviewDTO> reviewPage = reviewService.findByUserId(userId, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviewPage.getContent());
        response.put("currentPage", reviewPage.getNumber());
        response.put("totalItems", reviewPage.getTotalElements());
        response.put("totalPages", reviewPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Integer id) {
        return reviewService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{productId}/rating")
    public ResponseEntity<Map<String, Object>> getProductRating(@PathVariable Integer productId) {
        Double averageRating = reviewService.getAverageRating(productId);
        Long reviewCount = reviewService.getReviewCount(productId);

        Map<String, Object> response = new HashMap<>();
        response.put("averageRating", averageRating);
        response.put("reviewCount", reviewCount);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        if (reviewDTO.getReviewId() != null) {
            throw new BadRequestException("A new review cannot have an ID");
        }

        ReviewDTO createdReview = reviewService.save(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Integer id, @Valid @RequestBody ReviewDTO reviewDTO) {
        if (reviewDTO.getReviewId() != null && !reviewDTO.getReviewId().equals(id)) {
            throw new BadRequestException("Review ID in path and body must match");
        }

        ReviewDTO updatedReview = reviewService.update(id, reviewDTO);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
        try {
            reviewService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}