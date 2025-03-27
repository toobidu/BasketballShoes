package org.example.productservice.service.serviceimplement;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.ReviewDTO;
import org.example.productservice.entity.Review;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.mapper.ReviewMapper;
import org.example.productservice.repository.ReviewRepository;
import org.example.productservice.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;

    @Override
    public Page<ReviewDTO> findByProductId(Integer productId, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findByProduct_ProductId(productId, pageable);
        return reviewMapper.pageEntityToPageDto(reviewPage);
    }

    @Override
    public Page<ReviewDTO> findByUserId(Integer userId, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findByUserId(userId, pageable);
        return reviewMapper.pageEntityToPageDto(reviewPage);
    }

    @Override
    public Optional<ReviewDTO> findById(Integer id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::entityToDto);
    }

    @Override
    @Transactional
    public ReviewDTO save(ReviewDTO reviewDTO) {
        Review review = reviewMapper.dtoToEntity(reviewDTO);
        review = reviewRepository.save(review);
        return reviewMapper.entityToDto(review);
    }

    @Override
    @Transactional
    public ReviewDTO update(Integer id, ReviewDTO reviewDTO) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
        reviewDTO.setReviewId(id);
        Review review = reviewMapper.dtoToEntity(reviewDTO);
        review = reviewRepository.save(review);
        return reviewMapper.entityToDto(review);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }

    @Override
    public Double getAverageRating(Integer productId) {
        return reviewRepository.calculateAverageRating(productId);
    }

    @Override
    public Long getReviewCount(Integer productId) {
        return reviewRepository.countByProduct_ProductId(productId);
    }
}