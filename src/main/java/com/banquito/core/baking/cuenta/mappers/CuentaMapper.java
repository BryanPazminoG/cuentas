package com.banquito.core.baking.cuenta.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.dto.CuentaDTO;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    CuentaMapper mapper = Mappers.getMapper( CuentaMapper.class );
    
    Cuenta toEntity(CuentaDTO dto);
    @Mappings({
        @Mapping(target = "fechaCreacion", ignore = true),
        @Mapping(target = "fechaUltimoCambio", ignore = true),
        @Mapping(target = "version", ignore = true)
    })
    CuentaDTO toDTO(Cuenta entity);
}