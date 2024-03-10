package com.banquito.core.baking.cuenta.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.baking.cuenta.domain.Tarjeta;
import com.banquito.core.baking.cuenta.dto.TarjetaDTO;

@Mapper(componentModel = "spring")
public interface TarjetaMapper {
    TarjetaMapper mapper = Mappers.getMapper( TarjetaMapper.class );
    
    Tarjeta toEntity(TarjetaDTO dto);
    @Mappings({
        @Mapping(target = "fechaUltimoCambio", ignore = true),
        @Mapping(target = "version", ignore = true),
    })
    TarjetaDTO toDTO(Tarjeta entity);
}