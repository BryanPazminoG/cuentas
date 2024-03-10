package com.banquito.core.baking.cuenta.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.baking.cuenta.domain.TipoCuenta;
import com.banquito.core.baking.cuenta.dto.TipoCuentaDTO;

@Mapper(componentModel = "spring")
public interface TipoCuentaMapper {
    TipoCuentaMapper mapper = Mappers.getMapper( TipoCuentaMapper.class );
    
    TipoCuenta toEntity(TipoCuentaDTO dto);
    @Mappings({
        @Mapping(target = "fechaCreacion", ignore = true),
        @Mapping(target = "fechaUltimoCambio", ignore = true),
        @Mapping(target = "version", ignore = true)
    })
    TipoCuentaDTO toDTO(TipoCuenta entity);
}