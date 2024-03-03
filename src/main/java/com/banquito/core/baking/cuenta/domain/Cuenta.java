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
@Table(name = "CUENTAS")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CUENTA", nullable = false)
    private Integer codCuenta;

    @Column(name = "COD_TIPO_CUENTA", nullable = false, length = 10)
    private String codTipoCuenta;

    @Column(name = "COD_UNICO", nullable = false, length = 32)
    private String codUnico;

    @Column(name = "COD_CLIENTE", nullable = false, length = 32)
    private String codCliente;

    @Column(name = "NUMERO_CUENTA", nullable = false, length = 10)
    private String numeroCuenta;

    @Column(name = "SALDO_CONTABLE", nullable = false, precision = 18, scale = 2)
    private BigDecimal saldoContable;

    @Column(name = "SALDO_DISPONIBLE", nullable = false, precision = 18, scale = 2)
    private BigDecimal saldoDisponible;

    @Column(name = "MONTO_MAXIMO_RETIRO", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoMaximoRetiro;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaCreacion;

    @Column(name = "FECHA_ACTIVACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaActivacion;

    @Column(name = "FECHA_CIERRE", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaCierre;

    @Column(name = "FECHA_ULTIMO_CAMBIO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaUltimoCambio;

    @Version
    private Long version;

    public Cuenta() {
    }

    public Cuenta(Integer codCuenta) {
        this.codCuenta = codCuenta;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codCuenta == null) ? 0 : codCuenta.hashCode());
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
        Cuenta other = (Cuenta) obj;
        if (codCuenta == null) {
            if (other.codCuenta != null)
                return false;
        } else if (!codCuenta.equals(other.codCuenta))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cuenta [codCuenta=" + codCuenta + ", codTipoCuenta=" + codTipoCuenta + ", codUnico=" + codUnico
                + ", codCliente=" + codCliente + ", numeroCuenta=" + numeroCuenta + ", saldoContable=" + saldoContable
                + ", saldoDisponible=" + saldoDisponible + ", montoMaximoRetiro=" + montoMaximoRetiro + ", estado="
                + estado + ", fechaCreacion=" + fechaCreacion + ", fechaActivacion=" + fechaActivacion
                + ", fechaCierre=" + fechaCierre + ", fechaUltimoCambio=" + fechaUltimoCambio + ", version=" + version
                + "]";
    }

}