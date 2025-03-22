package org.example.productservice.mapper;

import org.example.productservice.dto.DailySaleDTO;
import org.example.productservice.entity.DailySale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailySaleMapper {
    DailySaleDTO toDailySalesDTO(DailySale dailySale);
    DailySale toDailySale(DailySaleDTO dailySaleDTO);
}
