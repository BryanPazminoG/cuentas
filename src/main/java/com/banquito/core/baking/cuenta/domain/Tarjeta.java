package com.banquito.core.baking.cuenta.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TARJETA")
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TARJETA", nullable = false)
    private Integer codTarjeta;

    @Column(name = "COD_CUENTA", nullable = false)
    private Integer codCuenta;

    @Column(name = "NUMERO", nullable = false, length = 18)
    private String numero;

    @Column(name = "FECHA_EMISION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaEmision;

    @Column(name = "FECHA_VIGENCIA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaVigencia;

    @Column(name = "CVV", nullable = false, length = 64)
    private String cvv;

    @Column(name = "TIPO_TARJETA", nullable = false, length = 3)
    private String tipoTarjeta;

    @Column(name = "RED_PAGO", nullable = false, length = 3)
    private String redPago;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @Column(name = "FECHA_ULTIMO_CAMBIO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaUltimoCambio;

    @Version
    private Long version;

    public Tarjeta() {
    }

    public Tarjeta(Integer codTarjeta) {
        this.codTarjeta = codTarjeta;
    }

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
        Tarjeta other = (Tarjeta) obj;
        if (codTarjeta == null) {
            if (other.codTarjeta != null)
                return false;
        } else if (!codTarjeta.equals(other.codTarjeta))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tarjeta [codTarjeta=" + codTarjeta + ", codCuenta=" + codCuenta + ", numero=" + numero
                + ", fechaEmision=" + fechaEmision + ", fechaVigencia=" + fechaVigencia + ", cvv=" + cvv
                + ", tipoTarjeta=" + tipoTarjeta + ", redPago=" + redPago + ", estado=" + estado
                + ", fechaUltimoCambio=" + fechaUltimoCambio + ", version=" + version + "]";
    }
    
}
