package org.example.productservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandsDTO {
    Integer brandId;
    String brandName;
    String logo;
    String brandDescription;
}
