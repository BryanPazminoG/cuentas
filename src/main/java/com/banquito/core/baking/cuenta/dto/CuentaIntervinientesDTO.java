package com.banquito.core.baking.cuenta.dto;

import java.util.Date;

import com.google.auto.value.AutoValue.Builder;

import lombok.Data;

@Builder
@Data
public class CuentaIntervinientesDTO {

    private CuentaIntervinientesPKDTO pk;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private Date fechaUltimoCambio;

    

    

    @Override
    public String toString() {
        return "CuentaIntervinienteDTO [pk=" + pk + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
                + ", estado=" + estado + ", fechaUltimoCambio=" + fechaUltimoCambio + "]";
    }





    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CuentaIntervinientesDTO other = (CuentaIntervinientesDTO) obj;
        if (pk == null) {
            if (other.pk != null)
                return false;
        } else if (!pk.equals(other.pk))
            return false;
        return true;
    }





    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
        return result;
    }

    

}
