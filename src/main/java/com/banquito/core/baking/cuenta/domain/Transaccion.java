package com.banquito.core.baking.cuenta.domain;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "TRANSACCION")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TRANSACCION", nullable = false)
    private Integer codTransaccion;

    @Column(name = "COD_CUENTA_ORIGEN", nullable = true)
    private Integer codCuentaOrigen;

    @Column(name = "COD_CUENTA_DESTINO", nullable = true)
    private Integer codCuentaDestino;

    @Column(name = "COD_UNICO", nullable = false, length = 64)
    private String codUnico;

    @Column(name = "TIPO_AFECTACION", nullable = false, length = 1)
    private String tipoAfectacion;

    @Column(name = "VALOR_DEBE", nullable = false, precision = 18, scale = 2)
    private BigDecimal valorDebe;

    @Column(name = "VALOR_HABER", nullable = false, precision = 18, scale = 2)
    private BigDecimal valorHaber;

    @Column(name = "TIPO_TRANSACCION", nullable = false, length = 8)
    private String tipoTransaccion;

    @Column(name = "DETALLE", nullable = false, length = 50)
    private String detalle;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @Column(name = "FECHA_AFECTACION", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAfectacion;

    @Column(name = "FECHA_ULTIMO_CAMBIO", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoCambio;

    @Version
    private Long version;

    // @ManyToOne()
    // @JoinColumn(name = "COD_CUENTA_ORIGEN", nullable = false, insertable = false,
    // updatable = false)
    // private Cuenta codCuentaOrigen;

    // @ManyToOne()
    // @JoinColumn(name = "COD_CUENTA_DESTINO", nullable = false, insertable =
    // false, updatable = false)
    // private Cuenta codCuentaDestino;

    public Transaccion() {
    }

    public Transaccion(Integer codTransaccion) {
        this.codTransaccion = codTransaccion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTransaccion == null) ? 0 : codTransaccion.hashCode());
        result = prime * result + ((codUnico == null) ? 0 : codUnico.hashCode());
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
        Transaccion other = (Transaccion) obj;
        if (codTransaccion == null) {
            if (other.codTransaccion != null)
                return false;
        } else if (!codTransaccion.equals(other.codTransaccion))
            return false;
        if (codUnico == null) {
            if (other.codUnico != null)
                return false;
        } else if (!codUnico.equals(other.codUnico))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Transaccion [codTransaccion=" + codTransaccion + ", codCuentaOrigen=" + codCuentaOrigen
                + ", codCuentaDestino=" + codCuentaDestino + ", codUnico=" + codUnico + ", tipoAfectacion="
                + tipoAfectacion + ", valorDebe=" + valorDebe + ", valorHaber=" + valorHaber + ", tipoTransaccion="
                + tipoTransaccion + ", detalle=" + detalle + ", fechaCreacion=" + fechaCreacion + ", estado=" + estado
                + ", fechaAfectacion=" + fechaAfectacion + ", fechaUltimoCambio=" + fechaUltimoCambio + ", version="
                + version + "]";
    }

}
