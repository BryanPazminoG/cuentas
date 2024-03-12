package com.banquito.core.baking.cuenta.dto;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CuentaIntervinientesDTO {

    private Integer codCuenta;
    private String codCliente;
    private String tipoInterviniente;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private String estado;

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
        if (codCliente == null) {
            if (other.codCliente != null)
                return false;
        } else if (!codCliente.equals(other.codCliente))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codCuenta == null) ? 0 : codCuenta.hashCode());
        result = prime * result + ((codCliente == null) ? 0 : codCliente.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CuentaIntervinientesDTO [codCuenta=" + codCuenta + ", codCliente=" + codCliente + ", tipoInterviniente="
                + tipoInterviniente + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado
                + "]";
    }
    
}
