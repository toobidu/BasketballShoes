package org.example.productservice.service.serviceimplement;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.CategoryDTO;
import org.example.productservice.entity.Category;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.mapper.CategoryMapper;
import org.example.productservice.repository.CategoryRepository;
import org.example.productservice.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.entitiesToDtos(categories);
    }

    @Override
    public Page<CategoryDTO> findWithPagination(String keyword, Pageable pageable) {
        Page<Category> categoryPage;
        if (keyword != null && !keyword.isEmpty()) {
            categoryPage = categoryRepository.findAllWithKeyword(keyword.toLowerCase(), pageable);
        } else {
            categoryPage = categoryRepository.findAll(pageable);
        }
        return categoryMapper.pageEntityToPageDto(categoryPage);
    }

    @Override
    public Optional<CategoryDTO> findById(Integer id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::entityToDto);
    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.dtoToEntity(categoryDTO);
        category = categoryRepository.save(category);
        return categoryMapper.entityToDto(category);
    }

    @Override
    @Transactional
    public CategoryDTO update(Integer id, CategoryDTO categoryDTO) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        categoryDTO.setCategoryId(id);
        Category category = categoryMapper.dtoToEntity(categoryDTO);
        category = categoryRepository.save(category);
        return categoryMapper.entityToDto(category);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsByCategoryName(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }
}