package com.banquito.core.baking.cuenta.dto;

import com.banquito.core.baking.cuenta.domain.Cuenta;

public class CuentaBuilder {

    public static CuentaDTO toDTO(Cuenta cuenta) {

        CuentaDTO dto = CuentaDTO.builder()
                .codCuenta(cuenta.getCodCuenta())
                .codTipoCuenta(cuenta.getCodTipoCuenta())
                .codUnico(cuenta.getCodUnico())
                .codCliente(cuenta.getCodCliente())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .saldoContable(cuenta.getSaldoContable())
                .saldoDisponible(cuenta.getSaldoDisponible())
                .montoMaximoRetiro(cuenta.getMontoMaximoRetiro())
                .estado(cuenta.getEstado())
                .fechaCreacion(cuenta.getFechaCreacion())
                .fechaActivacion(cuenta.getFechaActivacion())
                .fechaCierre(cuenta.getFechaCierre())
                .fechaUltimoCambio(cuenta.getFechaUltimoCambio())
                .build();
        return dto;
    }

    public static Cuenta toCuenta(CuentaDTO dto) {

        Cuenta cuenta = new Cuenta();
        cuenta.setCodCuenta(dto.getCodCuenta());
        cuenta.setCodTipoCuenta(dto.getCodTipoCuenta());
        cuenta.setCodUnico(dto.getCodUnico());
        cuenta.setCodCliente(dto.getCodCliente());
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setSaldoContable(dto.getSaldoContable());
        cuenta.setSaldoDisponible(dto.getSaldoDisponible());
        cuenta.setMontoMaximoRetiro(dto.getMontoMaximoRetiro());
        cuenta.setEstado(dto.getEstado());
        cuenta.setFechaActivacion(dto.getFechaActivacion());
        cuenta.setFechaCierre(dto.getFechaCierre());
        cuenta.setFechaUltimoCambio(dto.getFechaUltimoCambio());
        return cuenta;
    }

    public static Cuenta copyCuenta(Cuenta source, Cuenta destiny) {
        if (source.getCodCuenta() != null) {
            destiny.setCodCuenta(source.getCodCuenta());
        }

        if (source.getCodTipoCuenta() != null) {
            destiny.setCodTipoCuenta(source.getCodTipoCuenta());
        }

        if (source.getCodUnico() != null) {
            destiny.setCodUnico(source.getCodUnico());
        }

        if (source.getCodCliente() != null) {
            destiny.setCodCliente(source.getCodCliente());
        }

        if (source.getNumeroCuenta() != null) {
            destiny.setNumeroCuenta(source.getNumeroCuenta());
        }

        if (source.getSaldoContable() != null) {
            destiny.setSaldoContable(source.getSaldoContable());
        }

        if (source.getSaldoDisponible() != null) {
            destiny.setSaldoDisponible(source.getSaldoDisponible());
        }

        if (source.getMontoMaximoRetiro() != null) {
            destiny.setMontoMaximoRetiro(source.getMontoMaximoRetiro());
        }

        if (source.getEstado() != null) {
            destiny.setEstado(source.getEstado());
        }

        if (source.getFechaCreacion() != null) {
            destiny.setFechaCreacion(source.getFechaCreacion());
        }

        if (source.getFechaActivacion() != null) {
            destiny.setFechaActivacion(source.getFechaActivacion());
        }

        if (source.getFechaCierre() != null) {
            destiny.setFechaCierre(source.getFechaCierre());
        }

        if (source.getFechaUltimoCambio() != null) {
            destiny.setFechaUltimoCambio(source.getFechaUltimoCambio());
        }   
        return destiny;
    }
}
