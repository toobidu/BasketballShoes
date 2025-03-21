package org.example.productservice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)

public class ReviewsDTO {

    Integer reviewId;
    Integer userId;
    Integer productId;
    Integer rating;
    String comment;
}
