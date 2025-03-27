package org.example.productservice.mapper;

import org.example.productservice.dto.ReviewDTO;
import org.example.productservice.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    // Map from Entity to DTO
    @Mapping(source = "product.productId", target = "productId")
    ReviewDTO entityToDto(Review review);

    // Map from DTO to Entity
    @Mapping(target = "product.productId", source = "productId")
    Review dtoToEntity(ReviewDTO reviewDTO);

    default List<ReviewDTO> entitiesToDtos(List<Review> reviews) {
        if (reviews == null) {
            return null;
        }
        return reviews.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    default Page<ReviewDTO> pageEntityToPageDto(Page<Review> reviewPage) {
        List<ReviewDTO> reviewDTOs = entitiesToDtos(reviewPage.getContent());
        return new PageImpl<>(reviewDTOs, reviewPage.getPageable(), reviewPage.getTotalElements());
    }
}