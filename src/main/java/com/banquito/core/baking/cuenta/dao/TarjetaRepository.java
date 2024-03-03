package com.banquito.core.baking.cuenta.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.baking.cuenta.domain.Tarjeta;

@Repository
public interface TarjetaRepository extends CrudRepository<Tarjeta, Integer> {
    Tarjeta findByNumero(String numero);
}
