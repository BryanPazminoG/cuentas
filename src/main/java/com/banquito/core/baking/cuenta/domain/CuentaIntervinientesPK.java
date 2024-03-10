package com.banquito.core.baking.cuenta.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CuentaIntervinientesPK implements Serializable {
    @Column(name = "COD_CUENTA", nullable = false)
    private Integer codCuenta;

    @Column(name = "COD_CLIENTE", nullable = false, length =32)
    private String codCliente;

    public CuentaIntervinientesPK() {
    }

    public CuentaIntervinientesPK(Integer codCuenta, String codCliente) {
        this.codCuenta = codCuenta;
        this.codCliente = codCliente;
    }
}