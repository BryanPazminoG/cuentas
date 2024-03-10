package com.banquito.core.baking.cuenta.dto;

import java.util.Date;

import com.google.auto.value.AutoValue.Builder;

import lombok.Data;

@Builder
@Data
public class CuentaIntervinientesDTO {

    private Integer codCuenta;
    private Integer codClientePersona;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private Date fechaUltimoCambio;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CuentaIntervinientesDTO other = (CuentaIntervinientesDTO) obj;
        if (codCuenta == null) {
            if (other.codCuenta != null)
                return false;
        } else if (!codCuenta.equals(other.codCuenta))
            return false;
        if (codClientePersona == null) {
            if (other.codClientePersona != null)
                return false;
        } else if (!codClientePersona.equals(other.codClientePersona))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codCuenta == null) ? 0 : codCuenta.hashCode());
        result = prime * result + ((codClientePersona == null) ? 0 : codClientePersona.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CuentaIntervinientesDTO [codCuenta=" + codCuenta + ", codClientePersona=" + codClientePersona
                + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado
                + ", fechaUltimoCambio=" + fechaUltimoCambio + "]";
    }
}
