package org.example.productservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.CategoryDTO;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.example.productservice.exception.BadRequestException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/page")
    public ResponseEntity<Map<String, Object>> getCategoriesWithPagination(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "categoryId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<CategoryDTO> categoryPage = categoryService.findWithPagination(keyword, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("categories", categoryPage.getContent());
        response.put("currentPage", categoryPage.getNumber());
        response.put("totalItems", categoryPage.getTotalElements());
        response.put("totalPages", categoryPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        if (categoryDTO.getCategoryId() != null) {
            throw new BadRequestException("A new category cannot have an ID");
        }

        if (categoryService.existsByCategoryName(categoryDTO.getCategoryName())) {
            throw new BadRequestException("Category name already exists");
        }

        CategoryDTO createdCategory = categoryService.save(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
        if (categoryDTO.getCategoryId() != null && !categoryDTO.getCategoryId().equals(id)) {
            throw new BadRequestException("Category ID in path and body must match");
        }

        CategoryDTO updatedCategory = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        try {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}