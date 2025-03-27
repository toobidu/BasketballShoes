package org.example.productservice.mapper;

import org.example.productservice.dto.ProductDTO;
import org.example.productservice.entity.Brand;
import org.example.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Mapping from Entity to DTO
    @Mapping(source = "brand.brandId", target = "brandId")
    @Mapping(source = "productsDescription", target = "description")
    ProductDTO entityToDto(Product product);

    // Mapping from DTO to Entity
    @Mapping(target = "brand", expression = "java(mapBrand(productDTO))")
    @Mapping(source = "description", target = "productsDescription")
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Product dtoToEntity(ProductDTO productDTO);

    // Helper method to map Brand ID to Brand
    default Brand mapBrand(ProductDTO productDTO) {
        if (productDTO.getBrandId() == null) {
            return null;
        }
        Brand brand = new Brand();
        brand.setBrandId(productDTO.getBrandId());
        return brand;
    }

    // Convert list of entities to list of DTOs
    default List<ProductDTO> entitiesToDtos(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Convert Page of entities to Page of DTOs
    default Page<ProductDTO> pageEntityToPageDto(Page<Product> productPage) {
        List<ProductDTO> productDTOs = entitiesToDtos(productPage.getContent());
        return new PageImpl<>(productDTOs, productPage.getPageable(), productPage.getTotalElements());
    }
}