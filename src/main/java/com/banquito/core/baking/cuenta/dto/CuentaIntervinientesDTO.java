package com.banquito.core.baking.cuenta.dto;
import java.sql.Timestamp;

import com.google.auto.value.AutoValue.Builder;

import lombok.Data;

@Builder
@Data
public class CuentaIntervinientesDTO {

    private CuentaIntervinientesPKDTO pk;
    private String tipoInterviniente;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
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
        CuentaIntervinientesDTO other = (CuentaIntervinientesDTO) obj;
        if (tipoInterviniente == null) {
            if (other.tipoInterviniente != null)
                return false;
        } else if (!tipoInterviniente.equals(other.tipoInterviniente))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tipoInterviniente == null) ? 0 : tipoInterviniente.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CuentaIntervinientesDTO [pk=" + pk + ", tipoInterviniente=" + tipoInterviniente + ", fechaInicio="
                + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado + ", fechaUltimoCambio="
                + fechaUltimoCambio + "]";
    }

}
