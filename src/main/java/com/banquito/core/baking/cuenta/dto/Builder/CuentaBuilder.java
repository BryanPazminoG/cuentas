package com.banquito.core.baking.cuenta.dto.Builder;

import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.dto.CuentaDTO;

public class CuentaBuilder {

    public static CuentaDTO toDTO(Cuenta cuenta) {

        CuentaDTO dto = CuentaDTO.builder()
                .codCuenta(cuenta.getCodCuenta())
                .codTipoCuenta(cuenta.getCodTipoCuenta())
                .codCliente(cuenta.getCodCliente())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .saldoContable(cuenta.getSaldoContable())
                .saldoDisponible(cuenta.getSaldoDisponible())
                .montoMaximoRetiro(cuenta.getMontoMaximoRetiro())
                .estado(cuenta.getEstado())
                .fechaActivacion(cuenta.getFechaActivacion())
                .fechaCierre(cuenta.getFechaCierre())
                .build();
        return dto;
    }

    public static Cuenta toCuenta(CuentaDTO dto) {

        Cuenta cuenta = new Cuenta();
        cuenta.setCodCuenta(dto.getCodCuenta());
        cuenta.setCodTipoCuenta(dto.getCodTipoCuenta());
        cuenta.setCodCliente(dto.getCodCliente());
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setSaldoContable(dto.getSaldoContable());
        cuenta.setSaldoDisponible(dto.getSaldoDisponible());
        cuenta.setMontoMaximoRetiro(dto.getMontoMaximoRetiro());
        cuenta.setEstado(dto.getEstado());
        cuenta.setFechaActivacion(dto.getFechaActivacion());
        cuenta.setFechaCierre(dto.getFechaCierre());
        return cuenta;
    }

    public static Cuenta copyCuenta(Cuenta source, Cuenta destiny) {

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

        if (source.getFechaActivacion() != null) {
            destiny.setFechaActivacion(source.getFechaActivacion());
        }

        if (source.getFechaCierre() != null) {
            destiny.setFechaCierre(source.getFechaCierre());
        }

        return destiny;
    }
}
