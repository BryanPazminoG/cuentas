package com.banquito.core.baking.cuenta.dto.Builder;

import com.banquito.core.baking.cuenta.domain.PagoInteres;
import com.banquito.core.baking.cuenta.dto.PagoInteresDTO;

public class PagoInteresBuilder {

    public static PagoInteresDTO toDTO(PagoInteres pagoInteres){

        PagoInteresDTO dto = PagoInteresDTO.builder()
        .codPagoInteres(pagoInteres.getCodPagoInteres())
        .codCuenta(pagoInteres.getCodCuenta())
        .capital(pagoInteres.getCapital())
        .interesVigente(pagoInteres.getInteresVigente())
        .interesGanado(pagoInteres.getInteresGanado())
        .fechaEjecucion(pagoInteres.getFechaEjecucion())
        .build();
        return dto;
    }

    public static PagoInteres toPagoInteres(PagoInteresDTO dto){

        PagoInteres pagoInteres = new PagoInteres();
        pagoInteres.setCodPagoInteres(dto.getCodPagoInteres());
        pagoInteres.setCodCuenta(dto.getCodCuenta());
        pagoInteres.setCapital(dto.getCapital());
        pagoInteres.setInteresVigente(dto.getInteresVigente());
        pagoInteres.setInteresGanado(dto.getInteresGanado());
        pagoInteres.setFechaEjecucion(dto.getFechaEjecucion());
        return pagoInteres;

    }

    public static PagoInteres copyTasaInteres(PagoInteres source, PagoInteres destiny) {
        if (source.getCodPagoInteres() != null) {
            destiny.setCodPagoInteres(source.getCodPagoInteres());
        }

        if (source.getCodCuenta() != null) {
            destiny.setCodCuenta(source.getCodCuenta());
        }

        if (source.getFechaEjecucion() != null) {
            destiny.setFechaEjecucion(source.getFechaEjecucion());
        }

        if (source.getCapital() != null) {
            destiny.setCapital(source.getCapital());
        }

        if (source.getInteresGanado() != null) {
            destiny.setInteresGanado(source.getInteresGanado());
        }
        
        return destiny;
    }

}
