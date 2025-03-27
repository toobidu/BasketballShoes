package org.example.productservice.mapper;

import org.example.productservice.dto.SalesSummaryDTO;
import org.example.productservice.entity.SalesSummary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SalesSummaryMapper {
    SalesSummaryMapper INSTANCE = Mappers.getMapper(SalesSummaryMapper.class);

    // Direct mapping since productId is now a direct field
    SalesSummaryDTO entityToDto(SalesSummary salesSummary);

    // Direct mapping since productId is now a direct field
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