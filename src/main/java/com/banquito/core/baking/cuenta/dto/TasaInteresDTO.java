package com.banquito.core.baking.cuenta.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TasaInteresDTO {
    private Integer codTasaInteres;
    private BigDecimal tasaInteres;
    private Timestamp fechaVigencia;
    private String estado;

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
        return true;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTasaInteres == null) ? 0 : codTasaInteres.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "TasaInteresDTO [codTasaInteres=" + codTasaInteres + ", tasaInteres=" + tasaInteres + ", fechaVigencia="
                + fechaVigencia + ", estado=" + estado + "]";
    }

}
