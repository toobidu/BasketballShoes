package org.example.productservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandDTO {
    Integer brandId;
    String brandName;
    String logo;
    String brandDescription;
}
