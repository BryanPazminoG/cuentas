package com.banquito.core.baking.cuenta.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.baking.cuenta.domain.Tarjeta;
import java.util.Optional;

@Repository
public interface TarjetaRepository extends CrudRepository<Tarjeta, Integer> {

    Optional<Tarjeta> findByNumero(String numero);
    List<Tarjeta> findByCodCuentaOrderByFechaEmision(Integer codCuenta);
}