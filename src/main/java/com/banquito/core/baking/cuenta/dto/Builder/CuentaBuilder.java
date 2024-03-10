package com.banquito.core.baking.cuenta.dto.Builder;

import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.dto.CuentaDTO;

public class CuentaBuilder {

    public static CuentaDTO toDTO(Cuenta cuenta) {

        CuentaDTO dto = CuentaDTO.builder().codCuenta(cuenta.getCodCuenta())
                .codCliente(cuenta.getCodCliente())
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
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setSaldoContable(dto.getSaldoContable());
        cuenta.setSaldoDisponible(dto.getSaldoDisponible());
        return cuenta;
    }
}
