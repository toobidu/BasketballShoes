package org.example.productservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.BrandDTO;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.service.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@Validated
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> brands = brandService.findAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/page")
    public ResponseEntity<Map<String, Object>> getBrandsWithPagination(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "brandId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<BrandDTO> brandPage = brandService.findWithPagination(keyword, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("brands", brandPage.getContent());
        response.put("currentPage", brandPage.getNumber());
        response.put("totalItems", brandPage.getTotalElements());
        response.put("totalPages", brandPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Integer id) {
        return brandService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createBrand(@Valid @RequestBody BrandDTO brandDTO) {
        if (brandDTO.getBrandId() != null) {
            return ResponseEntity.badRequest().body("A new brand cannot have an ID");
        }

        if (brandService.existsByBrandName(brandDTO.getBrandName())) {
            return ResponseEntity.badRequest().body("Brand name already exists");
        }

        BrandDTO createdBrand = brandService.save(brandDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable Integer id, @Valid @RequestBody BrandDTO brandDTO) {
        try {
            BrandDTO updatedBrand = brandService.update(id, brandDTO);
            return ResponseEntity.ok(updatedBrand);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        try {
            brandService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}