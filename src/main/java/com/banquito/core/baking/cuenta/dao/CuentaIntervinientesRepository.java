package com.banquito.core.baking.cuenta.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.baking.cuenta.domain.CuentaIntervinientes;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientesPK;

//import io.opencensus.common.Timestamp;
import java.util.List;


@Repository
public interface CuentaIntervinientesRepository extends CrudRepository<CuentaIntervinientes, CuentaIntervinientesPK> {
    List<CuentaIntervinientes> findByPKCodCuenta(Integer codCuenta);
    List<CuentaIntervinientes> findByPKCodClientePersona(String codClientePersona);
}
