package com.banquito.core.baking.cuenta.dto;

import com.banquito.core.baking.cuenta.domain.Transaccion;

public class TransaccionBuilder {

    public static TransaccionDTO toDTO(Transaccion transaccion) {

        TransaccionDTO dto = TransaccionDTO.builder().codCuentaDestino(transaccion.getCodCuentaDestino())
                .codCuentaOrigen(transaccion.getCodCuentaOrigen())
                .codUnico(transaccion.getCodUnico())
                .detalle(transaccion.getDetalle())
                .estado(transaccion.getEstado())
                .fechaAfectacion(transaccion.getFechaAfectacion())
                .fechaUltimoCambio(transaccion.getFechaUltimoCambio()).build();
        return dto;
    }

    public static Transaccion toTransaccion(TransaccionDTO dto) {

        Transaccion transaccion = new Transaccion();
        transaccion.setTipoAfectacion(dto.getTipoAfectacion());
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
