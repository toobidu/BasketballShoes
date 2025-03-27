package org.example.productservice.service;

import org.example.productservice.dto.SalesSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SalesSummaryService {
    List<SalesSummaryDTO> findAll();

    Page<SalesSummaryDTO> findByProductId(Integer productId, Pageable pageable);

    Optional<SalesSummaryDTO> findById(Integer id);

    List<SalesSummaryDTO> findByProductIdAndPeriodType(Integer productId, String periodType);

    SalesSummaryDTO findByProductIdAndPeriodType(Integer productId, String periodType);

    SalesSummaryDTO save(SalesSummaryDTO salesSummaryDTO);

    SalesSummaryDTO update(Integer id, SalesSummaryDTO salesSummaryDTO);

    void deleteById(Integer id);
}