package com.banquito.core.baking.cuenta.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CuentaDTO {
    private Integer codCuenta;
    private String numeroCuenta;    
    private String codTipoCuenta;
    private String codCliente;
    private BigDecimal saldoContable;
    private BigDecimal saldoDisponible;
    private String estado;
    private Date fechaCreacion;
    private Date fechaUltimoCambio;

    @Override
    public String toString() {
        return "CuentaDTO [numeroCuenta=" + numeroCuenta + ", codTipoCuenta=" + codTipoCuenta + ", codCliente="
                + codCliente + ", saldoContable=" + saldoContable + ", saldoDisponible=" + saldoDisponible + ", estado="
                + estado + ", fechaCreacion=" + fechaCreacion + ", fechaUltimoCambio=" + fechaUltimoCambio + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CuentaDTO other = (CuentaDTO) obj;
        if (codCuenta == null) {
            if (other.codCuenta != null)
                return false;
        } else if (!codCuenta.equals(other.codCuenta))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codCuenta == null) ? 0 : codCuenta.hashCode());
        return result;
    }
}
