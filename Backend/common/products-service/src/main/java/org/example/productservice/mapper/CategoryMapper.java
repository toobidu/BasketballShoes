package org.example.productservice.mapper;

import org.example.productservice.dto.CategoryDTO;
import org.example.productservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO entityToDto(Category category);

    Category dtoToEntity(CategoryDTO categoryDTO);

    default List<CategoryDTO> entitiesToDtos(List<Category> categories) {
        return categories.stream()
                .map(this::entityToDto)
                .toList();
    }

    default Page<CategoryDTO> pageEntityToPageDto(Page<Category> categoryPage) {
        List<CategoryDTO> categoryDTOs = entitiesToDtos(categoryPage.getContent());
        return new PageImpl<>(categoryDTOs, categoryPage.getPageable(), categoryPage.getTotalElements());
    }

}
