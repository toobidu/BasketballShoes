package org.example.productservice.repository;

import org.example.productservice.entity.SalesSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesSummaryRepository extends JpaRepository<SalesSummary, Integer> {
    Page<SalesSummary> findByProduct_ProductId(Integer productId, Pageable pageable);
    
    List<SalesSummary> findByProduct_ProductIdAndPeriodType(Integer productId, String periodType);
    
    List<SalesSummary> findByProductIdAndPeriodType(Integer productId, PeriodType periodType);
}