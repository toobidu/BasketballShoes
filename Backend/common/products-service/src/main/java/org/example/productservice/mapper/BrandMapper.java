package org.example.productservice.mapper;

import org.example.productservice.dto.BrandDTO;
import org.example.productservice.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    // Ánh xạ từ Entity sang DTO
    BrandDTO entityToDto(Brand brand);

    // Ánh xạ từ DTO sang Entity
    Brand dtoToEntity(BrandDTO brandDTO);

    default List<BrandDTO> entitiesToDtos(List<Brand> brands) {
        if (brands == null) {
            return null;
        }
        return brands.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    default Page<BrandDTO> pageEntityToPageDto(Page<Brand> brandPage) {
        List<BrandDTO> brandDTOs = entitiesToDtos(brandPage.getContent());
        return new PageImpl<>(brandDTOs, brandPage.getPageable(), brandPage.getTotalElements());
    }
}