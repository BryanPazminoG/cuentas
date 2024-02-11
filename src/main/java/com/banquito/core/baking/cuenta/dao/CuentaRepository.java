package com.banquito.core.baking.cuenta.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.baking.cuenta.domain.Cuenta;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Integer>{
    Cuenta findByNumeroCuenta (String numeroCuenta);
    //List<Cuenta> findByCodClienteAndEstado (Integer codCliente, String estado);
    //List<Cuenta> findByCodCliente(Integer codCliente);
    List<Cuenta> findByFechaCreacionAndFechaUltimoCambioOrderByFechaUltimoCambio(Timestamp fechaCreacion, Timestamp fechaUltimoCambio);
    List<Cuenta> findByCodCliente(String codCliente);   


}