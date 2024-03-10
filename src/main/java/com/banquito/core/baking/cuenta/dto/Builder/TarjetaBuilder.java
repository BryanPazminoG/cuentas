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
        .fechaVencimiento(tarjeta.getFechaVigencia())
        .cvv(tarjeta.getCvv())
        .tipoTarjeta(tarjeta.getTipoTarjeta())
        .redPago(tarjeta.getRedPago())
        .estado(tarjeta.getEstado()).build();
        return dto;
    }

    public static Tarjeta toTarjeta(TarjetaDTO dto){

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setCodTarjeta(dto.getCodTarjeta());
        tarjeta.setCodCuenta(dto.getCodCuenta());
        tarjeta.setNumero(dto.getNumero());
        tarjeta.setFechaEmision(dto.getFechaEmision());
        tarjeta.setFechaVigencia(dto.getFechaVencimiento());
        tarjeta.setCvv(dto.getCvv());
        tarjeta.setTipoTarjeta(dto.getTipoTarjeta());
        tarjeta.setRedPago(dto.getRedPago());
        tarjeta.setEstado(dto.getEstado());
        return tarjeta;
    }
}
