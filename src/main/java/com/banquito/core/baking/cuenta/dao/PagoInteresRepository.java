package com.banquito.core.baking.cuenta.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.baking.cuenta.domain.PagoInteres;

@Repository
public interface PagoInteresRepository extends CrudRepository<PagoInteres, Integer>{
    
    List<PagoInteres> findByCodCuenta(Integer codCuenta);   

}

