package com.banquito.core.baking.cuenta.domain;

import java.math.BigDecimal;
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
@Table(name = "TASA_INTERES")
public class TasaInteres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TASA_INTERES", nullable = false)
    private Integer codTasaInteres;

    @Column(name = "COD_TIPO_CUENTA", nullable = false)
    private Integer codTipoCuenta; 

    @Column(name = "TASA_INTERES", nullable = false, precision = 18, scale = 2)
    private BigDecimal tasaInteres;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaCreacion;

    @Column(name = "FECHA_VIGENCIA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaVigencia;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @Column(name = "FECHA_ULTIMO_CAMBIO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaUltimoCambio;

    @Version
    private Long version;

    public TasaInteres() {
    }

    public TasaInteres(Integer codTasaInteres) {
        this.codTasaInteres = codTasaInteres;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTasaInteres == null) ? 0 : codTasaInteres.hashCode());
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
        TasaInteres other = (TasaInteres) obj;
        if (codTasaInteres == null) {
            if (other.codTasaInteres != null)
                return false;
        } else if (!codTasaInteres.equals(other.codTasaInteres))
            return false;
        return true;
    }

}