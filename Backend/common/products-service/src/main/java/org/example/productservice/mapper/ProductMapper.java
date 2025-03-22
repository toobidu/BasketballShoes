package org.example.productservice.mapper;

import org.example.productservice.dto.ProductDTO;
import org.example.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "type", source = "type.name")
    ProductDTO productToDTO(Product product);

    @Mapping(target = "brand.id", source = "brandId")
    @Mapping(target = "type", expression = "java(ProductType.valueOf(dto.getType()))")
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product dtoToProduct(ProductDTO dto);

    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "type", source = "type.name")
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "categories", ignore = true)
    void updateProductFromDTO(ProductDTO dto, @MappingTarget Product product);
}
