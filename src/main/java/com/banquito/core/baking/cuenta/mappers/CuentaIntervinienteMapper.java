package com.banquito.core.baking.cuenta.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.baking.cuenta.domain.CuentaIntervinientes;
import com.banquito.core.baking.cuenta.dto.CuentaIntervinientesDTO;

@Mapper(componentModel = "spring")
public interface CuentaIntervinienteMapper {
    CuentaIntervinienteMapper INSTANCE = Mappers.getMapper( CuentaIntervinienteMapper.class );

    CuentaIntervinientesDTO toDTO(CuentaIntervinientes entity);
    @Mappings({
        @Mapping(target = "", ignore = true),
    })
    CuentaIntervinientes toEntity(CuentaIntervinientesDTO dto);
    
}