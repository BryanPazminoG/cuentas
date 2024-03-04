package com.banquito.core.baking.cuenta.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PagoInteresDTO {
    private Integer codPagoInteres;
    private Integer codCuenta;
    private Timestamp fechaEjecucion;
    private BigDecimal capital;
    private BigDecimal interesGanado;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PagoInteresDTO other = (PagoInteresDTO) obj;
        if (codPagoInteres == null) {
            if (other.codPagoInteres != null)
                return false;
        } else if (!codPagoInteres.equals(other.codPagoInteres))
            return false;
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
        result = prime * result + ((codPagoInteres == null) ? 0 : codPagoInteres.hashCode());
        result = prime * result + ((codCuenta == null) ? 0 : codCuenta.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "PagoInteresDTO [codPagoInteres=" + codPagoInteres + ", codCuenta=" + codCuenta + ", fechaEjecucion="
                + fechaEjecucion + ", capital=" + capital + ", interesGanado=" + interesGanado + "]";
    }
    
}
