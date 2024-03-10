package com.banquito.core.baking.cuenta.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.baking.cuenta.domain.TasaInteres;

@Repository
public interface TasaInteresRepository extends CrudRepository<TasaInteres, Integer>{
    
    List<TasaInteres> findByEstadoOrderByFechaCreacion(String estado);

}