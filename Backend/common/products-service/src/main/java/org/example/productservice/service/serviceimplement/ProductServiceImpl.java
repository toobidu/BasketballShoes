package org.example.productservice.service.serviceimplement;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.ProductDTO;
import org.example.productservice.entity.Product;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.mapper.ProductMapper;
import org.example.productservice.repository.ProductRepository;
import org.example.productservice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.entitiesToDtos(products);
    }

    @Override
    public Page<ProductDTO> findWithFilters(String keyword, Integer brandId, 
                                          BigDecimal minPrice, BigDecimal maxPrice, 
                                          Boolean isAvailable, Pageable pageable) {
        Page<Product> productPage = productRepository.findAllWithFilters(
                keyword, brandId, minPrice, maxPrice, isAvailable, pageable);
        return productMapper.pageEntityToPageDto(productPage);
    }

    @Override
    public Optional<ProductDTO> findById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::entityToDto);
    }

    @Override
    public List<ProductDTO> findByBrandId(Integer brandId) {
        List<Product> products = productRepository.findByBrand_BrandId(brandId);
        return productMapper.entitiesToDtos(products);
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.dtoToEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.entityToDto(product);
    }

    @Override
    @Transactional
    public ProductDTO update(Integer id, ProductDTO productDTO) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productDTO.setProductId(id);
        Product product = productMapper.dtoToEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.entityToDto(product);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public boolean existsByProductName(String productName) {
        return productRepository.existsByProductName(productName);
    }
}