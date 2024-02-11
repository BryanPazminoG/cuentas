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
@Table(name = "CUENTA")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CUENTA", nullable = false)
    private Integer codCuenta;

    @Column(name = "NUMERO_CUENTA", nullable = false, length = 10)
    private String numeroCuenta;

    @Column(name = "COD_TIPO_CUENTA", nullable = false, length = 10)
    private String codTipoCuenta;

    @Column(name = "COD_CLIENTE", nullable = true, length = 64)
    private String codCliente;

    @Column(name = "SALDO_CONTABLE", nullable = false, precision = 18, scale = 2)
    private BigDecimal saldoContable;

    @Column(name = "SALDO_DISPONIBLE", nullable = false, precision = 18, scale = 2)
    private BigDecimal saldoDisponible;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "FECHA_ULTIMO_CAMBIO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoCambio;

    @Version
    private Long version;

    // @ManyToOne()
    // @JoinColumn(name = "COD_TIPO_CUENTA", nullable = false, updatable = false,
    // insertable = false)
    // private TipoCuenta tipoCuenta;

    // @OneToMany()
    // private List<Transaccion> transaccion;

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
        return "Cuenta [codCuenta=" + codCuenta + ", numeroCuenta=" + numeroCuenta + ", codTipoCuenta=" + codTipoCuenta
                + ", codCliente=" + codCliente + ", saldoContable=" + saldoContable + ", saldoDisponible="
                + saldoDisponible + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + ", fechaUltimoCambio="
                + fechaUltimoCambio + "]";
    }

    

}
