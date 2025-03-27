package org.example.productservice.service;

import org.example.productservice.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> findAll();

    Page<ProductDTO> findWithFilters(String keyword, Integer brandId, 
                                   BigDecimal minPrice, BigDecimal maxPrice, 
                                   Boolean isAvailable, Pageable pageable);

    Optional<ProductDTO> findById(Integer id);

    List<ProductDTO> findByBrandId(Integer brandId);

    ProductDTO save(ProductDTO productDTO);

    ProductDTO update(Integer id, ProductDTO productDTO);

    void deleteById(Integer id);

    boolean existsByProductName(String productName);
}