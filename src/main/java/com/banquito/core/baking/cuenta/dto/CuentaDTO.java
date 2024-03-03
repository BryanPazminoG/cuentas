package com.banquito.core.baking.cuenta.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CuentaDTO {
    private Integer codCuenta;   
    private String codTipoCuenta;
    private String codUnico;
    private String codCliente;
    private String numeroCuenta; 
    private BigDecimal saldoContable;
    private BigDecimal saldoDisponible;
    private BigDecimal montoMaximoRetiro;
    private String estado;
    private Date fechaCreacion;
    private Date fechaActivacion;
    private Date fechaCierre;
    private Date fechaUltimoCambio;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CuentaDTO other = (CuentaDTO) obj;
        if (codUnico == null) {
            if (other.codUnico != null)
                return false;
        } else if (!codUnico.equals(other.codUnico))
            return false;
        if (numeroCuenta == null) {
            if (other.numeroCuenta != null)
                return false;
        } else if (!numeroCuenta.equals(other.numeroCuenta))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codUnico == null) ? 0 : codUnico.hashCode());
        result = prime * result + ((numeroCuenta == null) ? 0 : numeroCuenta.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "CuentaDTO [codCuenta=" + codCuenta + ", codTipoCuenta=" + codTipoCuenta + ", codUnico=" + codUnico
                + ", codCliente=" + codCliente + ", numeroCuenta=" + numeroCuenta + ", saldoContable=" + saldoContable
                + ", saldoDisponible=" + saldoDisponible + ", montoMaximoRetiro=" + montoMaximoRetiro + ", estado="
                + estado + ", fechaCreacion=" + fechaCreacion + ", fechaActivacion=" + fechaActivacion
                + ", fechaCierre=" + fechaCierre + ", fechaUltimoCambio=" + fechaUltimoCambio + "]";
    }
    
}
