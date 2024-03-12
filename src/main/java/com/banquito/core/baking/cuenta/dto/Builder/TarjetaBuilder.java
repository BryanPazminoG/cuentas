package com.banquito.core.baking.cuenta.dto.Builder;

import com.banquito.core.baking.cuenta.domain.Tarjeta;
import com.banquito.core.baking.cuenta.dto.TarjetaDTO;

public class TarjetaBuilder {

    public static TarjetaDTO toDTO(Tarjeta tarjeta){

        TarjetaDTO dto = TarjetaDTO.builder()
        .codTarjeta(tarjeta.getCodTarjeta())
        .codCuenta(tarjeta.getCodCuenta())
        .numero(tarjeta.getNumero())
        .fechaEmision(tarjeta.getFechaEmision())
        .fechaVigencia(tarjeta.getFechaVigencia())
        .cvc(tarjeta.getCvc())
        .tipoTarjeta(tarjeta.getTipoTarjeta())
        .redPago(tarjeta.getRedPago())
        .estado(tarjeta.getEstado())
        .build();
        return dto;
    }

    public static Tarjeta toTarjeta(TarjetaDTO dto){

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setCodTarjeta(dto.getCodTarjeta());
        tarjeta.setCodCuenta(dto.getCodCuenta());
        tarjeta.setNumero(dto.getNumero());
        tarjeta.setFechaEmision(dto.getFechaEmision());
        tarjeta.setFechaVigencia(dto.getFechaVigencia());
        tarjeta.setCvc(dto.getCvc());
        tarjeta.setTipoTarjeta(dto.getTipoTarjeta());
        tarjeta.setRedPago(dto.getRedPago());
        tarjeta.setEstado(dto.getEstado());
        return tarjeta;

    }

    public static Tarjeta copyTarjeta(Tarjeta source, Tarjeta destiny) {
        if (source.getCodTarjeta() != null) {
            destiny.setCodTarjeta(source.getCodTarjeta());
        }
        if (source.getCodCuenta() != null) {
            destiny.setCodCuenta(source.getCodCuenta());
        }
        if (source.getNumero() != null) {
            destiny.setNumero(source.getNumero());
        }

        if (source.getFechaEmision() != null) {
            destiny.setFechaEmision(source.getFechaEmision());
        }

        if (source.getFechaVigencia() != null) {
            destiny.setFechaVigencia(source.getFechaVigencia());
        }

        if (source.getCvc() != null) {
            destiny.setCvc(source.getCvc());
        }
        
        if (source.getTipoTarjeta() != null) {
            destiny.setTipoTarjeta(source.getTipoTarjeta());
        }

        if (source.getRedPago() != null) {
            destiny.setRedPago(source.getRedPago());
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
