package com.banquito.core.baking.cuenta.dto;

import com.banquito.core.baking.cuenta.domain.Transaccion;

public class TransaccionBuilder {

    public static TransaccionDTO toDTO(Transaccion transaccion) {

        TransaccionDTO dto = TransaccionDTO.builder()
                .codCuentaOrigen(transaccion.getCodCuentaOrigen())
                .codCuentaDestino(transaccion.getCodCuentaDestino())
                .tipoAfectacion(transaccion.getTipoAfectacion())
                .valorDebe(transaccion.getValorDebe())
                .valorHaber(transaccion.getValorHaber())
                .tipoTransaccion(transaccion.getTipoTransaccion())
                .codUnico(transaccion.getCodUnico())
                .detalle(transaccion.getDetalle())
                .estado(transaccion.getEstado())
                .fechaCreacion(transaccion.getFechaCreacion())
                .fechaAfectacion(transaccion.getFechaAfectacion())
                .fechaUltimoCambio(transaccion.getFechaUltimoCambio()).build();
        return dto;
    }

    public static Transaccion toTransaccion(TransaccionDTO dto) {

        Transaccion transaccion = new Transaccion();
        transaccion.setTipoAfectacion(dto.getTipoAfectacion());
        transaccion.setCodUnico(dto.getCodUnico());
        transaccion.setCodCuentaOrigen(dto.getCodCuentaOrigen());
        transaccion.setCodCuentaDestino(dto.getCodCuentaDestino());
        transaccion.setValorDebe(dto.getValorDebe());
        transaccion.setValorHaber(dto.getValorHaber());
        transaccion.setTipoTransaccion(dto.getTipoTransaccion());
        transaccion.setDetalle(dto.getDetalle());
        transaccion.setFechaCreacion(dto.getFechaCreacion());
        transaccion.setEstado(dto.getEstado());
        transaccion.setFechaAfectacion(dto.getFechaAfectacion());
        transaccion.setFechaUltimoCambio(dto.getFechaUltimoCambio());
        return transaccion;
    }

}