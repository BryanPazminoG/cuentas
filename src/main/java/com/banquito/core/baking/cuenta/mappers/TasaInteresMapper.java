package com.banquito.core.baking.cuenta.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.baking.cuenta.domain.TasaInteres;
import com.banquito.core.baking.cuenta.dto.TasaInteresDTO;

@Mapper(componentModel = "spring")
public interface TasaInteresMapper {
    TasaInteresMapper mapper = Mappers.getMapper( TasaInteresMapper.class );

    TasaInteres toEntity(TasaInteresDTO dto);
    @Mappings({
        @Mapping(target = "fechaCreacion", ignore = true),
        @Mapping(target = "fechaUltimoCambio", ignore = true),
        @Mapping(target = "version", ignore = true)
    })
    TasaInteresDTO toDTO(TasaInteres entity);
}