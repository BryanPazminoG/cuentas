package com.banquito.core.baking.cuenta.dto;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class TarjetaDTO {

    private Integer codTarjeta;
    private Integer codCuenta;
    private String numero;
    private Timestamp fechaEmision;
    private Timestamp fechaVencimiento;
    private String cvc;
    private String pin;
    private String tipoTarjeta;
    private String redPago;
    private String estado;
    private Date fechaUltimoCambio;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTarjeta == null) ? 0 : codTarjeta.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TarjetaDTO other = (TarjetaDTO) obj;
        if (codTarjeta == null) {
            if (other.codTarjeta != null)
                return false;
        } else if (!codTarjeta.equals(other.codTarjeta))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TarjetaDTO [codTarjeta=" + codTarjeta + ", codCuenta=" + codCuenta + ", numero=" + numero
                + ", fechaEmision=" + fechaEmision + ", fechaVencimiento=" + fechaVencimiento + ", cvc=" + cvc
                + ", pin=" + pin + ", tipoTarjeta=" + tipoTarjeta + ", redPago=" + redPago + ", estado=" + estado
                + ", fechaUltimoCambio=" + fechaUltimoCambio + "]";
    }

    

}
