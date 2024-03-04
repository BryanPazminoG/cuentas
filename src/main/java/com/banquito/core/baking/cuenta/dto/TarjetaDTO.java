package com.banquito.core.baking.cuenta.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class TarjetaDTO {

    private Integer codTarjeta;
    private Integer codCuenta;
    private String numero;
    private Timestamp fechaEmision;
    private Timestamp fechaVigencia;
    private String cvv;
    private String tipoTarjeta;
    private String redPago;
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
        TarjetaDTO other = (TarjetaDTO) obj;
        if (codCuenta == null) {
            if (other.codCuenta != null)
                return false;
        } else if (!codCuenta.equals(other.codCuenta))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codCuenta == null) ? 0 : codCuenta.hashCode());
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "TarjetaDTO [codTarjeta=" + codTarjeta + ", codCuenta=" + codCuenta + ", numero=" + numero
                + ", fechaEmision=" + fechaEmision + ", fechaVigencia=" + fechaVigencia + ", cvv=" + cvv
                + ", tipoTarjeta=" + tipoTarjeta + ", redPago=" + redPago + ", estado=" + estado
                + ", fechaUltimoCambio=" + fechaUltimoCambio + "]";
    }

}
