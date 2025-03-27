package org.example.productservice.mapper;

import org.example.productservice.dto.DailySaleDTO;
import org.example.productservice.entity.DailySale;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DailySaleMapper {
    DailySaleMapper INSTANCE = Mappers.getMapper(DailySaleMapper.class);

    // Map from Entity to DTO
    DailySaleDTO entityToDto(DailySale dailySale);

    // Map from DTO to Entity
    DailySale dtoToEntity(DailySaleDTO dailySaleDTO);

    default List<DailySaleDTO> entitiesToDtos(List<DailySale> dailySales) {
        if (dailySales == null) {
            return null;
        }
        return dailySales.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    default Page<DailySaleDTO> pageEntityToPageDto(Page<DailySale> dailySalePage) {
        List<DailySaleDTO> dailySaleDTOs = entitiesToDtos(dailySalePage.getContent());
        return new PageImpl<>(dailySaleDTOs, dailySalePage.getPageable(), dailySalePage.getTotalElements());
    }
}