package org.example.productservice.service;

import org.example.productservice.dto.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<BrandDTO> findAll();

    Page<BrandDTO> findWithPagination(String keyword, Pageable pageable);

    Optional<BrandDTO> findById(Integer id);

    BrandDTO save(BrandDTO brandDTO);

    BrandDTO update(Integer id, BrandDTO brandDTO);

    void deleteById(Integer id);

    boolean existsByBrandName(String brandName);
}