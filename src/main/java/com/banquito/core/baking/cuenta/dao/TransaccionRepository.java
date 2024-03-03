package com.banquito.core.baking.cuenta.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.baking.cuenta.domain.Transaccion;


@Repository
public interface TransaccionRepository extends CrudRepository<Transaccion, Integer>{
   Transaccion findByCodCuentaOrigenAnd (Integer codCuentaOrigen);


}
