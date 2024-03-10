package com.banquito.core.baking.cuenta.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.banquito.core.baking.cuenta.domain.PagoInteres;
import com.banquito.core.baking.cuenta.dto.PagoInteresDTO;

@Mapper(componentModel = "spring")
public interface PagoInteresMapper {
    PagoInteresMapper mapper = Mappers.getMapper( PagoInteresMapper.class );

    PagoInteresDTO toDTO(PagoInteres entity);
    PagoInteres toEntity(PagoInteresDTO dto); 
}