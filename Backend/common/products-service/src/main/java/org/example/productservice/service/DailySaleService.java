package org.example.productservice.service;

import org.example.productservice.dto.DailySaleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailySaleService {
    List<DailySaleDTO> findAll();

    Page<DailySaleDTO> findByProductId(Integer productId, Pageable pageable);

    Optional<DailySaleDTO> findById(Integer id);

    List<DailySaleDTO> findByProductIdAndDateRange(Integer productId, LocalDate startDate, LocalDate endDate);

    DailySaleDTO findByProductIdAndDate(Integer productId, LocalDate date);

    DailySaleDTO save(DailySaleDTO dailySaleDTO);

    DailySaleDTO update(Integer id, DailySaleDTO dailySaleDTO);

    void deleteById(Integer id);
}