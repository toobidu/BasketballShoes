package org.example.productservice.mapper;

import org.example.productservice.dto.BrandsDTO;
import org.example.productservice.dto.CategoriesDTO;
import org.example.productservice.dto.ProductsDTO;
import org.example.productservice.entity.Brand;
import org.example.productservice.entity.Category;
import org.example.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "categories", source = "categories")
    ProductsDTO productToDTO(Product product);

    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "categories", source = "categories")
    Product dtoToProduct(ProductsDTO dto);

    default BrandsDTO brandToDTO(Brand brand) {
        return BrandMapper.INSTANCE.brandToDTO(brand);
    }

    default Brand dtoToBrand(BrandsDTO dto) {
        return BrandMapper.INSTANCE.dtoToBrand(dto);
    }

    default Set<CategoriesDTO> categoriesToDTOs(Set<Category> categories) {
        return categories.stream()
                .map(CategoryMapper.INSTANCE::categoryToDTO)
                .collect(Collectors.toSet());
    }

    default Set<Category> dtoToCategories(Set<CategoriesDTO> dtos) {
        return dtos.stream()
                .map(CategoryMapper.INSTANCE::dtoToCategory)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "categories", ignore = true)
    void updateProductFromDTO(ProductsDTO dto, @MappingTarget Product product);
}
