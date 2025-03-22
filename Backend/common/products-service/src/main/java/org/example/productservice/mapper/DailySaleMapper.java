package org.example.productservice.mapper;

import org.example.productservice.dto.DailySalesDTO;
import org.example.productservice.entity.DailySale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailySaleMapper {
    DailySalesDTO toDailySalesDTO(DailySale dailySale);
    DailySale toDailySale(DailySalesDTO dailySalesDTO);
}
