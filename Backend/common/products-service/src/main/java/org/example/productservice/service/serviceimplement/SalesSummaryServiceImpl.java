package org.example.productservice.service.serviceimplement;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.SalesSummaryDTO;
import org.example.productservice.entity.SalesSummary;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.mapper.SalesSummaryMapper;
import org.example.productservice.repository.SalesSummaryRepository;
import org.example.productservice.service.SalesSummaryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalesSummaryServiceImpl implements SalesSummaryService {

    private final SalesSummaryRepository salesSummaryRepository;
    private final SalesSummaryMapper salesSummaryMapper = SalesSummaryMapper.INSTANCE;

    @Override
    public List<SalesSummaryDTO> findAll() {
        List<SalesSummary> salesSummaries = salesSummaryRepository.findAll();
        return salesSummaryMapper.entitiesToDtos(salesSummaries);
    }

    @Override
    public Page<SalesSummaryDTO> findByProductId(Integer productId, Pageable pageable) {
        Page<SalesSummary> salesSummaryPage = salesSummaryRepository.findByProduct_ProductId(productId, pageable);
        return salesSummaryMapper.pageEntityToPageDto(salesSummaryPage);
    }

    @Override
    public Optional<SalesSummaryDTO> findById(Integer id) {
        return salesSummaryRepository.findById(id)
                .map(salesSummaryMapper::entityToDto);
    }

    @Override
    public List<SalesSummaryDTO> findAllByProductIdAndPeriodType(Integer productId, String periodType) {
        List<SalesSummary> salesSummaries = salesSummaryRepository.findByProduct_ProductIdAndPeriodType(productId, periodType);
        return salesSummaryMapper.entitiesToDtos(salesSummaries);
    }

    @Override
    public Optional<SalesSummaryDTO> findSingleByProductIdAndPeriodType(Integer productId, String periodType) {
        SalesSummary salesSummary = salesSummaryRepository.findByProductIdAndPeriodType(productId, periodType);
        return Optional.ofNullable(salesSummary).map(salesSummaryMapper::entityToDto);
    }

    @Override
    @Transactional
    public SalesSummaryDTO save(SalesSummaryDTO salesSummaryDTO) {
        SalesSummary salesSummary = salesSummaryMapper.dtoToEntity(salesSummaryDTO);
        salesSummary = salesSummaryRepository.save(salesSummary);
        return salesSummaryMapper.entityToDto(salesSummary);
    }

    @Override
    @Transactional
    public SalesSummaryDTO update(Integer id, SalesSummaryDTO salesSummaryDTO) {
        if (!salesSummaryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sales summary not found with id: " + id);
        }
        salesSummaryDTO.setSalesSummaryId(id);
        SalesSummary salesSummary = salesSummaryMapper.dtoToEntity(salesSummaryDTO);
        salesSummary = salesSummaryRepository.save(salesSummary);
        return salesSummaryMapper.entityToDto(salesSummary);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (!salesSummaryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sales summary not found with id: " + id);
        }
        salesSummaryRepository.deleteById(id);
    }
}