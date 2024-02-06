package com.banquito.core.baking.cuenta.dto;

import com.banquito.core.baking.cuenta.domain.Cuenta;

public class CuentaBuilder {

    public static CuentaDTO toDTO(Cuenta cuenta) {

        CuentaDTO dto = CuentaDTO.builder().codCliente(cuenta.getCodCliente())
                .codTipoCuenta(cuenta.getCodTipoCuenta())
                .estado(cuenta.getEstado())
                .fechaCreacion(cuenta.getFechaCreacion())
                .fechaUltimoCambio(cuenta.getFechaUltimoCambio())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .saldoContable(cuenta.getSaldoContable())
                .saldoDisponible(cuenta.getSaldoDisponible()).build();
        return dto;
    }

    public static Cuenta toCuenta(CuentaDTO dto) {

        Cuenta cuenta = new Cuenta();
        cuenta.setCodCliente(dto.getCodCliente());
        cuenta.setCodTipoCuenta(dto.getCodTipoCuenta());
        cuenta.setEstado(dto.getEstado());
        cuenta.setFechaCreacion(dto.getFechaCreacion());
        cuenta.setFechaUltimoCambio(dto.getFechaUltimoCambio());
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setSaldoContable(dto.getSaldoContable());
        cuenta.setSaldoDisponible(dto.getSaldoDisponible());
        return cuenta;
    }

    public static Cuenta copyCuenta(Cuenta source, Cuenta destiny) {
        if (source.getCodCliente() != null) {
            destiny.setCodCliente(source.getCodCliente());
        }

        if (source.getCodCuenta() != null) {
            destiny.setCodCuenta(source.getCodCuenta());
        }

        if (source.getCodTipoCuenta() != null) {
            destiny.setCodTipoCuenta(source.getCodTipoCuenta());
        }

        destiny.setEstado(source.getEstado());
        destiny.setFechaCreacion(source.getFechaCreacion());
        destiny.setFechaUltimoCambio(source.getFechaUltimoCambio());

        if (source.getNumeroCuenta() != null) {
            destiny.setNumeroCuenta(source.getNumeroCuenta());
        }

        if (source.getSaldoContable() != null) {
            destiny.setSaldoContable(source.getSaldoContable());
        }

        if (source.getSaldoDisponible() != null) {
            destiny.setSaldoDisponible(source.getSaldoDisponible());
        }
        return destiny;
    }
}
