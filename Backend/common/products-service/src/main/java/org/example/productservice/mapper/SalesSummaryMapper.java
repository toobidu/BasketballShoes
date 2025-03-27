package org.example.productservice.mapper;

import org.example.productservice.dto.SalesSummaryDTO;
import org.example.productservice.entity.SalesSummary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SalesSummaryMapper {
    SalesSummaryMapper INSTANCE = Mappers.getMapper(SalesSummaryMapper.class);

    // Map from Entity to DTO
    @Mapping(source = "product.productId", target = "productId")
    SalesSummaryDTO entityToDto(SalesSummary salesSummary);

    // Map from DTO to Entity
    @Mapping(target = "product.productId", source = "productId")
    SalesSummary dtoToEntity(SalesSummaryDTO salesSummaryDTO);

    default List<SalesSummaryDTO> entitiesToDtos(List<SalesSummary> salesSummaries) {
        if (salesSummaries == null) {
            return null;
        }
        return salesSummaries.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    default Page<SalesSummaryDTO> pageEntityToPageDto(Page<SalesSummary> salesSummaryPage) {
        List<SalesSummaryDTO> salesSummaryDTOs = entitiesToDtos(salesSummaryPage.getContent());
        return new PageImpl<>(salesSummaryDTOs, salesSummaryPage.getPageable(), salesSummaryPage.getTotalElements());
    }
}