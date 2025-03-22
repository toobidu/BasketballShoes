package org.example.productservice.mapper;

import org.example.productservice.dto.CategoriesDTO;
import org.example.productservice.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoriesDTO toCategoryDTO(Category category);
    Category toCategory(CategoriesDTO categoriesDTO);
}
