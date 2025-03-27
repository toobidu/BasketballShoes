package org.example.productservice.repository;

import org.example.productservice.entity.DailySale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailySaleRepository extends JpaRepository<DailySale, Integer> {
    Page<DailySale> findByProductId(Integer productId, Pageable pageable);
    
    List<DailySale> findByProductIdAndSaleDateBetween(Integer productId, LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT ds FROM DailySale ds WHERE ds.saleDate = :date AND ds.productId = :productId")
    DailySale findByProductIdAndDate(Integer productId, LocalDate date);
}