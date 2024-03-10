package com.banquito.core.baking.cuenta.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.baking.cuenta.domain.Cuenta;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Integer>{

    Optional<Cuenta> findByNumeroCuenta (String numeroCuenta);
    List<Cuenta> findByFechaCreacionAndFechaUltimoCambioOrderByFechaUltimoCambio(Timestamp fechaCreacion, Timestamp fechaUltimoCambio);
    List<Cuenta> findByCodClienteOrderByFechaCreacion(String codCliente);   

}