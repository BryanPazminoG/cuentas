package com.banquito.core.baking.cuenta.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.baking.cuenta.domain.Transaccion;
import com.banquito.core.baking.cuenta.dto.TransaccionDTO;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {
    TransaccionMapper mapper = Mappers.getMapper( TransaccionMapper.class );

    TransaccionDTO toDTO(Transaccion entity);
    @Mappings({
        @Mapping(target = "fechaCreacion", ignore = true),
        @Mapping(target = "fechaUltimoCambio", ignore = true),
        @Mapping(target = "fechaAfectacion", ignore = true),
        @Mapping(target = "version", ignore = true)
    })
    Transaccion toEntity(TransaccionDTO dto);
}