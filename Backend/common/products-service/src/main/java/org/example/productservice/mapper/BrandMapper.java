package org.example.productservice.mapper;

import org.example.productservice.dto.BrandDTO;
import org.example.productservice.dto.request.BrandRequestDTO;
import org.example.productservice.dto.response.BrandResponseDTO;
import org.example.productservice.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "brandName", target = "name")
    @Mapping(source = "logo", target = "logoUrl")
    @Mapping(source = "brandDescription", target = "description")
    BrandResponseDTO toResponseDTO(Brand entity);

    @Mapping(target = "brandId", ignore = true)
    @Mapping(source = "name", target = "brandName")
    @Mapping(source = "logoUrl", target = "logo")
    @Mapping(source = "description", target = "brandDescription")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Brand fromRequestDTO(BrandRequestDTO requestDTO);

    @Mapping(target = "brandId", ignore = true)
    @Mapping(source = "name", target = "brandName")
    @Mapping(source = "logoUrl", target = "logo")
    @Mapping(source = "description", target = "brandDescription")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(BrandRequestDTO dto, @MappingTarget Brand entity);
}
