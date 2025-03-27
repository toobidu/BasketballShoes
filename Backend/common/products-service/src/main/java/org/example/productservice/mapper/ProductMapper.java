package org.example.productservice.mapper;

import org.example.productservice.dto.ProductDTO;
import org.example.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Map from Entity to DTO
    @Mapping(source = "brand.brandId", target = "brandId")
    @Mapping(source = "productsDescription", target = "description")
    ProductDTO entityToDto(Product product);

    // Map from DTO to Entity
    @Mapping(target = "brand.brandId", source = "brandId")
    @Mapping(source = "description", target = "productsDescription")
    Product dtoToEntity(ProductDTO productDTO);

    default List<ProductDTO> entitiesToDtos(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    default Page<ProductDTO> pageEntityToPageDto(Page<Product> productPage) {
        List<ProductDTO> productDTOs = entitiesToDtos(productPage.getContent());
        return new PageImpl<>(productDTOs, productPage.getPageable(), productPage.getTotalElements());
    }
}