package com.banquito.core.baking.cuenta.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TasaInteresDTO {
    private Integer codTasaInteres;
    private Integer codTipoCuenta;
    private BigDecimal tasaInteres;
    private Timestamp fechaCreacion;
    private Timestamp fechaVigencia;
    private String estado;
    private Timestamp fechaUltimoCambio;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TasaInteresDTO other = (TasaInteresDTO) obj;
        if (codTasaInteres == null) {
            if (other.codTasaInteres != null)
                return false;
        } else if (!codTasaInteres.equals(other.codTasaInteres))
            return false;
        if (codTipoCuenta == null) {
            if (other.codTipoCuenta != null)
                return false;
        } else if (!codTipoCuenta.equals(other.codTipoCuenta))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTasaInteres == null) ? 0 : codTasaInteres.hashCode());
        result = prime * result + ((codTipoCuenta == null) ? 0 : codTipoCuenta.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "TasaInteresDTO [codTasaInteres=" + codTasaInteres + ", codTipoCuenta=" + codTipoCuenta
                + ", tasaInteres=" + tasaInteres + ", fechaCreacion=" + fechaCreacion + ", fechaVigencia="
                + fechaVigencia + ", estado=" + estado + ", fechaUltimoCambio=" + fechaUltimoCambio + "]";
    }
    
}
