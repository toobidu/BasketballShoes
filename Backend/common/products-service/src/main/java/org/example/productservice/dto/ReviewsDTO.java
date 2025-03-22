package org.example.productservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsDTO {
    Integer reviewId;
    Integer userId;
    Integer productId;
    Integer rating;
    String comment;
}
