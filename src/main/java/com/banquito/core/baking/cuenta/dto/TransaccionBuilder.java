package com.banquito.core.baking.cuenta.dto;

import com.banquito.core.baking.cuenta.domain.Transaccion;

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
                .fechaCreacion(transaccion.getFechaCreacion())
                .estado(transaccion.getEstado())
                .fechaAfectacion(transaccion.getFechaAfectacion())
                .fechaUltimoCambio(transaccion.getFechaUltimoCambio()).build();
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
        return transaccion;
    }

}