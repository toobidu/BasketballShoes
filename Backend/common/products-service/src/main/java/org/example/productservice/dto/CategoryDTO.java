package org.example.productservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    Integer categoryId;
    String categoryName;
    String categoryDescription;
}
