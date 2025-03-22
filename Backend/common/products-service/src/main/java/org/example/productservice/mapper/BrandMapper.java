package org.example.productservice.mapper;

import org.example.productservice.dto.BrandsDTO;
import org.example.productservice.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandsDTO toBrandDTO(Brand brand);
    Brand toBrand(BrandsDTO brandsDTO);
}
