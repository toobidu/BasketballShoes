package org.example.productservice.service;

import org.example.productservice.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO> findAll();

    Page<CategoryDTO> findWithPagination(String keyword, Pageable pageable);

    Optional<CategoryDTO> findById(Integer id);

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO update(Integer id, CategoryDTO categoryDTO);

    void deleteById(Integer id);

    boolean existsByCategoryName(String categoryName);
}