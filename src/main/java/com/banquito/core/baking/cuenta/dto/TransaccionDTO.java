package com.banquito.core.baking.cuenta.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransaccionDTO {

    private Integer codTransaccion;
    private Integer codCuenta;
    private String codUnico;
    private String tipoAfectacion;
    private BigDecimal valorDebe;
    private BigDecimal valorHaber;
    private String tipoTransaccion;
    private String canal;
    private String detalle;
    private String estado;
    private Date fechaAfectacion;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TransaccionDTO other = (TransaccionDTO) obj;
        if (codTransaccion == null) {
            if (other.codTransaccion != null)
                return false;
        } else if (!codTransaccion.equals(other.codTransaccion))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTransaccion == null) ? 0 : codTransaccion.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "TransaccionDTO [codTransaccion=" + codTransaccion + ", codCuenta=" + codCuenta + ", codUnico="
                + codUnico + ", tipoAfectacion=" + tipoAfectacion + ", valorDebe=" + valorDebe + ", valorHaber="
                + valorHaber + ", tipoTransaccion=" + tipoTransaccion + ", canal=" + canal + ", detalle=" + detalle
                + ", estado=" + estado + ", fechaAfectacion=" + fechaAfectacion + "]";
    }

}
