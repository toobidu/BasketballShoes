package org.example.productservice.service.serviceimplement;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.BrandDTO;
import org.example.productservice.entity.Brand;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.mapper.BrandMapper;
import org.example.productservice.repository.BrandRepository;
import org.example.productservice.service.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper = BrandMapper.INSTANCE;

    @Override
    public List<BrandDTO> findAll() {
        List<Brand> brands = brandRepository.findAll();
        return brandMapper.entitiesToDtos(brands);
    }

    @Override
    public Page<BrandDTO> findWithPagination(String keyword, Pageable pageable) {
        Page<Brand> brandPage;
        if (keyword != null && !keyword.isEmpty()) {
            brandPage = brandRepository.findAllWithKeyword(keyword.toLowerCase(), pageable);
        } else {
            brandPage = brandRepository.findAll(pageable);
        }
        return brandMapper.pageEntityToPageDto(brandPage);
    }

    @Override
    public Optional<BrandDTO> findById(Integer id) {
        return brandRepository.findById(id)
                .map(brandMapper::entityToDto);
    }

    @Override
    @Transactional
    public BrandDTO save(BrandDTO brandDTO) {
        Brand brand = brandMapper.dtoToEntity(brandDTO);
        brand = brandRepository.save(brand);
        return brandMapper.entityToDto(brand);
    }

    @Override
    @Transactional
    public BrandDTO update(Integer id, BrandDTO brandDTO) {
        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException("Brand not found with id: " + id);
        }
        brandDTO.setBrandId(id);
        Brand brand = brandMapper.dtoToEntity(brandDTO);
        brand = brandRepository.save(brand);
        return brandMapper.entityToDto(brand);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException("Brand not found with id: " + id);
        }
        brandRepository.deleteById(id);
    }

    @Override
    public boolean existsByBrandName(String brandName) {
        return brandRepository.existsByBrandName(brandName);
    }
}