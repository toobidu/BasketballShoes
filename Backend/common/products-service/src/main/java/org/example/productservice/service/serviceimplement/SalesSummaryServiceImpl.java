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
        // Update to use findByProductId instead of findByProduct_ProductId
        Page<SalesSummary> salesSummaryPage = salesSummaryRepository.findByProductId(productId, pageable);
        return salesSummaryMapper.pageEntityToPageDto(salesSummaryPage);
    }

    @Override
    public Optional<SalesSummaryDTO> findById(Integer id) {
        return salesSummaryRepository.findById(id)
                .map(salesSummaryMapper::entityToDto);
    }

    @Override
    public List<SalesSummaryDTO> findByProductIdAndPeriodType(Integer productId, String periodType) {
        // Update to use findByProductIdAndPeriodType
        List<SalesSummary> salesSummaries = salesSummaryRepository.findByProductIdAndPeriodType(productId, periodType);
        return salesSummaryMapper.entitiesToDtos(salesSummaries);
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