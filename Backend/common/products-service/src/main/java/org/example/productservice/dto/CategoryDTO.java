package org.example.productservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    Integer categoryId;
    String categoryName;
    String categoryDescription;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
