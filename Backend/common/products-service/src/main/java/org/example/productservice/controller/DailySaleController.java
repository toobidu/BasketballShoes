package org.example.productservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.DailySaleDTO;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.service.DailySaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.productservice.exception.BadRequestException;

@RestController
@RequestMapping("/api/daily-sales")
@RequiredArgsConstructor
@Validated
public class DailySaleController {

    private final DailySaleService dailySaleService;

    @GetMapping
    public ResponseEntity<List<DailySaleDTO>> getAllDailySales() {
        List<DailySaleDTO> dailySales = dailySaleService.findAll();
        return ResponseEntity.ok(dailySales);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Map<String, Object>> getDailySalesByProductId(
            @PathVariable Integer productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "saleDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<DailySaleDTO> dailySalePage = dailySaleService.findByProductId(productId, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("dailySales", dailySalePage.getContent());
        response.put("currentPage", dailySalePage.getNumber());
        response.put("totalItems", dailySalePage.getTotalElements());
        response.put("totalPages", dailySalePage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/{productId}/date-range")
    public ResponseEntity<List<DailySaleDTO>> getDailySalesByProductIdAndDateRange(
            @PathVariable Integer productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<DailySaleDTO> dailySales = dailySaleService.findByProductIdAndDateRange(productId, startDate, endDate);
        return ResponseEntity.ok(dailySales);
    }

    @GetMapping("/product/{productId}/date/{date}")
    public ResponseEntity<DailySaleDTO> getDailySaleByProductIdAndDate(
            @PathVariable Integer productId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        try {
            DailySaleDTO dailySale = dailySaleService.findByProductIdAndDate(productId, date);
            return ResponseEntity.ok(dailySale);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailySaleDTO> getDailySaleById(@PathVariable Integer id) {
        return dailySaleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createDailySale(@Valid @RequestBody DailySaleDTO dailySaleDTO) {
        if (dailySaleDTO.getSaleId() != null) {
            throw new BadRequestException("A new daily sale cannot have an ID");
        }

        DailySaleDTO createdDailySale = dailySaleService.save(dailySaleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDailySale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDailySale(@PathVariable Integer id, @Valid @RequestBody DailySaleDTO dailySaleDTO) {
        if (dailySaleDTO.getSaleId() != null && !dailySaleDTO.getSaleId().equals(id)) {
            throw new BadRequestException("Daily Sale ID in path and body must match");
        }

        DailySaleDTO updatedDailySale = dailySaleService.update(id, dailySaleDTO);
        return ResponseEntity.ok(updatedDailySale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDailySale(@PathVariable Integer id) {
        try {
            dailySaleService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}