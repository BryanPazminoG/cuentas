package com.banquito.core.baking.cuenta.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.baking.cuenta.domain.CuentaIntervinientes;
import com.banquito.core.baking.cuenta.dto.CuentaIntervinientesDTO;

@Mapper(componentModel = "spring")
public interface CuentaIntervinienteMapper {
    CuentaIntervinienteMapper mapper = Mappers.getMapper( CuentaIntervinienteMapper.class );

    CuentaIntervinientes toEntity(CuentaIntervinientesDTO dto);
    @Mappings({
        @Mapping(target = "fechaUltimoCambio", ignore = true),
        @Mapping(target = "version", ignore = true)
    })
    CuentaIntervinientesDTO toDTO(CuentaIntervinientes entity);
}