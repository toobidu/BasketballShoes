package org.example.productservice.service.serviceimplement;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.DailySaleDTO;
import org.example.productservice.entity.DailySale;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.mapper.DailySaleMapper;
import org.example.productservice.repository.DailySaleRepository;
import org.example.productservice.service.DailySaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailySaleServiceImpl implements DailySaleService {

    private final DailySaleRepository dailySaleRepository;
    private final DailySaleMapper dailySaleMapper = DailySaleMapper.INSTANCE;

    @Override
    public List<DailySaleDTO> findAll() {
        List<DailySale> dailySales = dailySaleRepository.findAll();
        return dailySaleMapper.entitiesToDtos(dailySales);
    }

    @Override
    public Page<DailySaleDTO> findByProductId(Integer productId, Pageable pageable) {
        Page<DailySale> dailySalePage = dailySaleRepository.findByProductId(productId, pageable);
        return dailySaleMapper.pageEntityToPageDto(dailySalePage);
    }

    @Override
    public Optional<DailySaleDTO> findById(Integer id) {
        return dailySaleRepository.findById(id)
                .map(dailySaleMapper::entityToDto);
    }

    @Override
    public List<DailySaleDTO> findByProductIdAndDateRange(Integer productId, LocalDate startDate, LocalDate endDate) {
        List<DailySale> dailySales = dailySaleRepository.findByProductIdAndSaleDateBetween(productId, startDate, endDate);
        return dailySaleMapper.entitiesToDtos(dailySales);
    }

    @Override
    public DailySaleDTO findByProductIdAndDate(Integer productId, LocalDate date) {
        DailySale dailySale = dailySaleRepository.findByProductIdAndDate(productId, date);
        if (dailySale == null) {
            throw new ResourceNotFoundException("Daily sale not found for product id: " + productId + " and date: " + date);
        }
        return dailySaleMapper.entityToDto(dailySale);
    }

    @Override
    @Transactional
    public DailySaleDTO save(DailySaleDTO dailySaleDTO) {
        DailySale dailySale = dailySaleMapper.dtoToEntity(dailySaleDTO);
        dailySale = dailySaleRepository.save(dailySale);
        return dailySaleMapper.entityToDto(dailySale);
    }

    @Override
    @Transactional
    public DailySaleDTO update(Integer id, DailySaleDTO dailySaleDTO) {
        if (!dailySaleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Daily sale not found with id: " + id);
        }
        dailySaleDTO.setDailySaleId(id);
        DailySale dailySale = dailySaleMapper.dtoToEntity(dailySaleDTO);
        dailySale = dailySaleRepository.save(dailySale);
        return dailySaleMapper.entityToDto(dailySale);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (!dailySaleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Daily sale not found with id: " + id);
        }
        dailySaleRepository.deleteById(id);
    }
}