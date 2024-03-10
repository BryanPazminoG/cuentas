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
        @Mapping(target = "", ignore = true),
    })
    Transaccion toEntity(TransaccionDTO dto);
}