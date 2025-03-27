package org.example.productservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.SalesSummaryDTO;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.service.SalesSummaryService;
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
@RequestMapping("/api/sales-summary")
@RequiredArgsConstructor
@Validated
public class SalesSummaryController {

    private final SalesSummaryService salesSummaryService;

    @GetMapping
    public ResponseEntity<List<SalesSummaryDTO>> getAllSalesSummaries() {
        List<SalesSummaryDTO> salesSummaries = salesSummaryService.findAll();
        return ResponseEntity.ok(salesSummaries);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Map<String, Object>> getSalesSummariesByProductId(
            @PathVariable Integer productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "periodDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<SalesSummaryDTO> salesSummaryPage = salesSummaryService.findByProductId(productId, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("salesSummaries", salesSummaryPage.getContent());
        response.put("currentPage", salesSummaryPage.getNumber());
        response.put("totalItems", salesSummaryPage.getTotalElements());
        response.put("totalPages", salesSummaryPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/{productId}/period/{periodType}")
    public ResponseEntity<List<SalesSummaryDTO>> getSalesSummariesByProductIdAndPeriodType(
            @PathVariable Integer productId,
            @PathVariable String periodType
    ) {
        List<SalesSummaryDTO> salesSummaries = salesSummaryService.findByProductIdAndPeriodType(productId, periodType);
        return ResponseEntity.ok(salesSummaries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesSummaryDTO> getSalesSummaryById(@PathVariable Integer id) {
        return salesSummaryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createSalesSummary(@Valid @RequestBody SalesSummaryDTO salesSummaryDTO) {
        if (salesSummaryDTO.getSalesSummaryId() != null) {
            return ResponseEntity.badRequest().body("A new sales summary cannot have an ID");
        }

        SalesSummaryDTO createdSalesSummary = salesSummaryService.save(salesSummaryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSalesSummary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSalesSummary(@PathVariable Integer id, @Valid @RequestBody SalesSummaryDTO salesSummaryDTO) {
        try {
            SalesSummaryDTO updatedSalesSummary = salesSummaryService.update(id, salesSummaryDTO);
            return ResponseEntity.ok(updatedSalesSummary);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalesSummary(@PathVariable Integer id) {
        try {
            salesSummaryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}