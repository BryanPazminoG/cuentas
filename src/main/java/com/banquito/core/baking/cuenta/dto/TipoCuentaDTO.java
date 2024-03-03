package com.banquito.core.baking.cuenta.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TipoCuentaDTO {
    private String codTipoCuenta;
    private String codTasaInteres;
    private String nombre;
    private String descripcion;
    private String tipoCapitalizacion;
    private String formaCapitalizacion;
    private Integer maximoNumeroIntervinientes;
    private Date fechaCreacion;
    private Date fechaUltimoCambio;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TipoCuentaDTO other = (TipoCuentaDTO) obj;
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
        result = prime * result + ((codTipoCuenta == null) ? 0 : codTipoCuenta.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "TipoCuentaDTO [codTipoCuenta=" + codTipoCuenta + ", codTasaInteres=" + codTasaInteres + ", nombre="
                + nombre + ", descripcion=" + descripcion + ", tipoCapitalizacion=" + tipoCapitalizacion
                + ", formaCapitalizacion=" + formaCapitalizacion + ", maximoNumeroIntervinientes="
                + maximoNumeroIntervinientes + ", fechaCreacion=" + fechaCreacion + ", fechaUltimoCambio="
                + fechaUltimoCambio + "]";
    }
}
