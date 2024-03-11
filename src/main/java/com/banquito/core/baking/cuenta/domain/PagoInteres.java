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

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "PAGO_INTERES")
public class PagoInteres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_PAGO_INTERES", nullable = false)
    private Integer codPagoInteres;

    @Column(name = "COD_CUENTA", nullable = false)
    private Integer codCuenta; 

    @Column(name = "CAPITAL", nullable = false, precision = 18, scale = 2)
    private BigDecimal capital;

    @Column(name = "INTERES_VIGENTE", nullable = false, precision = 18, scale = 2)
    private BigDecimal interesVigente;

    @Column(name = "INTERES_GANADO", nullable = false, precision = 18, scale = 2)
    private BigDecimal interesGanado;

    @Column(name = "FECHA_EJECUCION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaEjecucion;

    public PagoInteres() {
    }

    public PagoInteres(Integer codPagoInteres) {
        this.codPagoInteres = codPagoInteres;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codPagoInteres == null) ? 0 : codPagoInteres.hashCode());
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
        PagoInteres other = (PagoInteres) obj;
        if (codPagoInteres == null) {
            if (other.codPagoInteres != null)
                return false;
        } else if (!codPagoInteres.equals(other.codPagoInteres))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PagoInteres [codPagoInteres=" + codPagoInteres + ", codCuenta=" + codCuenta + ", capital=" + capital
                + ", interesVigente=" + interesVigente + ", interesGanado=" + interesGanado + ", fechaEjecucion="
                + fechaEjecucion + "]";
    }
}