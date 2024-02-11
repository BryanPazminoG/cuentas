package com.banquito.core.baking.cuenta.dto;

import com.banquito.core.baking.cuenta.domain.Tarjeta;

public class TarjetaBuilder {

    public static TarjetaDTO toDTO(Tarjeta tarjeta){

        TarjetaDTO dto = TarjetaDTO.builder()
        .codTarjeta(tarjeta.getCodTarjeta())
        .codCuenta(tarjeta.getCodCuenta())
        .numero(tarjeta.getNumero())
        .fechaEmision(tarjeta.getFechaEmision())
        .fechaVencimiento(tarjeta.getFechaVencimiento())
        .cvc(tarjeta.getCvc())
        .pin(tarjeta.getPin())
        .tipoTarjeta(tarjeta.getTipoTarjeta())
        .redPago(tarjeta.getRedPago())
        .estado(tarjeta.getEstado())
        .fechaUltimoCambio(tarjeta.getFechaUltimoCambio())
        .build();
        return dto;
    }

    public static Tarjeta toTarjeta(TarjetaDTO dto){

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setCodTarjeta(dto.getCodTarjeta());
        tarjeta.setCodCuenta(dto.getCodCuenta());
        tarjeta.setNumero(dto.getNumero());
        tarjeta.setFechaEmision(dto.getFechaEmision());
        tarjeta.setFechaVencimiento(dto.getFechaVencimiento());
        tarjeta.setCvc(dto.getCvc());
        tarjeta.setPin(dto.getPin());
        tarjeta.setTipoTarjeta(dto.getTipoTarjeta());
        tarjeta.setRedPago(dto.getRedPago());
        tarjeta.setEstado(dto.getEstado());
        tarjeta.setFechaUltimoCambio(dto.getFechaUltimoCambio());
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
        destiny.setFechaEmision(source.getFechaEmision());
        destiny.setFechaVencimiento(source.getFechaVencimiento());
        if (source.getCvc() != null) {
            destiny.setCvc(source.getCvc());
        }
        if (source.getPin() != null) {
            destiny.setPin(source.getPin());
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
        destiny.setFechaUltimoCambio(source.getFechaUltimoCambio());
        return destiny;
    }

}
