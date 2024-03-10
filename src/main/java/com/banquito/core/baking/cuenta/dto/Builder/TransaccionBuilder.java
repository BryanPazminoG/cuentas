package com.banquito.core.baking.cuenta.dto.Builder;

import com.banquito.core.baking.cuenta.domain.Transaccion;
import com.banquito.core.baking.cuenta.dto.TransaccionDTO;

public class TransaccionBuilder {

    public static TransaccionDTO toDTO(Transaccion transaccion) {

        TransaccionDTO dto = TransaccionDTO.builder()
                .codTransaccion(transaccion.getCodTransaccion())
                .codCuenta(transaccion.getCodCuenta())
                .codUnico(transaccion.getCodUnico())
                .tipoAfectacion(transaccion.getTipoAfectacion())
                .valorDebe(transaccion.getValorDebe())
                .valorHaber(transaccion.getValorHaber())
                .tipoTransaccion(transaccion.getTipoTransaccion())
                .canal(transaccion.getCanal())
                .detalle(transaccion.getDetalle())
                .estado(transaccion.getEstado())
                .fechaAfectacion(transaccion.getFechaAfectacion())
                .build();
        return dto;
    }

    public static Transaccion toTransaccion(TransaccionDTO dto) {

        Transaccion transaccion = new Transaccion();
        transaccion.setCodTransaccion(dto.getCodTransaccion());
        transaccion.setCodCuenta(dto.getCodCuenta());
        transaccion.setCodUnico(dto.getCodUnico());
        transaccion.setTipoAfectacion(dto.getTipoAfectacion());
        transaccion.setValorDebe(dto.getValorDebe());
        transaccion.setValorHaber(dto.getValorHaber());
        transaccion.setTipoTransaccion(dto.getTipoTransaccion());
        transaccion.setCanal(dto.getCanal());
        transaccion.setDetalle(dto.getDetalle());
        transaccion.setEstado(dto.getEstado());
        transaccion.setFechaAfectacion(dto.getFechaAfectacion());
        return transaccion;
    }

}