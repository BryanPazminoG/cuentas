package com.banquito.core.baking.cuenta.dto;

import com.banquito.core.baking.cuenta.domain.TasaInteres;

public class TasaInteresBuilder {

    public static TasaInteresDTO toDTO(TasaInteres tasaInteres){
        TasaInteresDTO dto = TasaInteresDTO.builder()
            .codTasaInteres(tasaInteres.getCodTasaInteres())
            .tasaInteres(tasaInteres.getTasaInteres())
            .fechaCreacion(tasaInteres.getFechaCreacion())
            .fechaVigencia(tasaInteres.getFechaVigencia())
            .estado(tasaInteres.getEstado())
            .fechaUltimoCambio(tasaInteres.getFechaUltimoCambio())
            .build();
        return dto;
    }

    public static TasaInteres toTasaInteres(TasaInteresDTO dto){

        TasaInteres tasaInteres = new TasaInteres();
        tasaInteres.setCodTasaInteres(dto.getCodTasaInteres());
        tasaInteres.setTasaInteres(dto.getTasaInteres());
        tasaInteres.setFechaCreacion(dto.getFechaCreacion());
        tasaInteres.setFechaVigencia(dto.getFechaVigencia());
        tasaInteres.setEstado(dto.getEstado());
        tasaInteres.setFechaUltimoCambio(dto.getFechaUltimoCambio());
        return tasaInteres;

    }

    public static TasaInteres copyTasaInteres(TasaInteres source, TasaInteres destiny) {
        if (source.getCodTasaInteres() != null) {
            destiny.setCodTasaInteres(source.getCodTasaInteres());
        }

        if (source.getTasaInteres() != null) {
            destiny.setTasaInteres(source.getTasaInteres());
        }

        if (source.getFechaCreacion() != null) {
            destiny.setFechaCreacion(source.getFechaCreacion());
        }

        if (source.getFechaVigencia() != null) {
            destiny.setFechaVigencia(source.getFechaVigencia());
        }

        if (source.getEstado() != null) {
            destiny.setEstado(source.getEstado());
        }

        if (source.getFechaUltimoCambio() != null) {
            destiny.setFechaUltimoCambio(source.getFechaUltimoCambio());
        }
        
        return destiny;
    }

}
