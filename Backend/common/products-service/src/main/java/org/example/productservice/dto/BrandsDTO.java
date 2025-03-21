package org.example.productservice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class BrandsDTO {

    Integer brandId;
    String brandName;
    String logo;
    String brandDescription;
}
