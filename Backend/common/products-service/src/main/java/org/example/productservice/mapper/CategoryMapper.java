package org.example.productservice.mapper;

import org.example.productservice.dto.CategoryDTO;
import org.example.productservice.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toCategoryDTO(Category category);
    Category toCategory(CategoryDTO categoryDTO);
}
