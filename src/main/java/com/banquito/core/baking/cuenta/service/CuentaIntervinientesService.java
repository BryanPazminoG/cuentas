package com.banquito.core.baking.cuenta.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.banquito.core.baking.cuenta.dao.CuentaIntervinientesRepository;
import com.banquito.core.baking.cuenta.dao.CuentaRepository;
import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientes;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientesPK;
import com.banquito.core.baking.cuenta.dto.CuentaIntervinientesDTO;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CuentaIntervinientesService {
    
    private final CuentaIntervinientesRepository cuentaIntervinientesRepository;
    private final CuentaRepository cuentaRepository;

    public CuentaIntervinientesService(CuentaIntervinientesRepository cuentaIntervinientesRepository,
            CuentaRepository cuentaRepository) {
        this.cuentaIntervinientesRepository = cuentaIntervinientesRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public Optional<CuentaIntervinientes> getById(Integer codCuenta, Integer codClientePersona) {
        CuentaIntervinientesPK cuentaIntervinientePK = new CuentaIntervinientesPK(codCuenta, codClientePersona);
        log.info("Se encontro la cuenta {} con el codigo cliente {}", codCuenta, codClientePersona);
        return this.cuentaIntervinientesRepository.findById(cuentaIntervinientePK);
    }

    public Iterable<CuentaIntervinientes> getByCuenta(Integer codCuenta) {
        log.info("Se encontro la cuenta: {}", codCuenta);
        return this.cuentaIntervinientesRepository.findByPKCodCuenta(codCuenta);
    }

    public Iterable<CuentaIntervinientes> getByCodCliente(Integer CodClientePersona) {
        log.info("Se encontro el cliente {}", CodClientePersona);
        return this.cuentaIntervinientesRepository.findByPKCodClientePersona(CodClientePersona);
    }

    public List<Cuenta> getCuentaByInter(Integer CodClientePersona) {
        log.info("Va a obtener las cuentas del cliente:{}", CodClientePersona);
        try {
            Iterable<CuentaIntervinientes> listaIntervinientes = getByCodCliente(CodClientePersona);
            List<Cuenta> listaCuentas = new ArrayList<>();
            for (CuentaIntervinientes intervinientes : listaIntervinientes) {
                Optional<Cuenta> cuenta = this.cuentaRepository.findById(intervinientes.getPK().getCodCuenta());
                if (cuenta.isPresent()) {
                    log.debug("Cuenta obtenida: {}",cuenta.get());
                    listaCuentas.add(cuenta.get());
                }
            }
            return listaCuentas;

        } catch (Exception e) {
            throw new CreacionException(
                    "Error al buscar las cuentas del cliente con codigo: " + CodClientePersona + ", Error: " + e,
                    e);
        }
    }

    @Transactional
    public CuentaIntervinientes crear(CuentaIntervinientesDTO dto ) {
        try {
            CuentaIntervinientes cuentaIntervinientes = new CuentaIntervinientes();
            cuentaIntervinientes.setEstado(dto.getEstado());

            Timestamp fechaFinTimestamp = new Timestamp(dto.getFechaFin().getSeconds() * 1000L);
            Timestamp fechaInicioTimestamp = new Timestamp(dto.getFechaInicio().getSeconds() * 1000L);
            Timestamp fechaUltimoCambioTimestamp = new Timestamp(dto.getFechaUltimoCambio().getSeconds() * 1000L);

            cuentaIntervinientes.setFechaFin(fechaFinTimestamp);
            cuentaIntervinientes.setFechaInicio(fechaInicioTimestamp);
            cuentaIntervinientes.setFechaUltimoCambio(fechaUltimoCambioTimestamp);
            
            log.info("Se creo la cuenta: {}", cuentaIntervinientes);
            return this.cuentaIntervinientesRepository.save(cuentaIntervinientes);

        } catch (Exception e) {
            throw new CreacionException(
                    "Error en creacion de los Intervinientes en la cuenta: " + dto + ", Error: " + e,
                    e);
        }
    }

    public CuentaIntervinientes actualizar(CuentaIntervinientesDTO cuentaIntervinientesdto) {
        try {
            Optional<CuentaIntervinientes> cuentaIntervinientesOptional = getById(
                    cuentaIntervinientesdto.getPk().getCodCuenta(),
                    cuentaIntervinientesdto.getPk().getCodClientePersona());
    
            if (cuentaIntervinientesOptional.isPresent()) {
                CuentaIntervinientes cuentaIntervinientes = cuentaIntervinientesOptional.get();
    
                cuentaIntervinientes.setEstado(cuentaIntervinientesdto.getEstado());
    
                log.info("La cuenta {} se actualizó correctamente", cuentaIntervinientes);
                return crear(cuentaIntervinientesdto);
            } else {
                throw new RuntimeException(
                        "La cuenta Intervinientes con id " + cuentaIntervinientesdto.getPk().getCodCuenta() + "-"
                                + cuentaIntervinientesdto.getPk().getCodClientePersona() + " no existe");
            }
        } catch (Exception e) {
            throw new CreacionException("Ocurrió un error al actualizar la cuenta, error: " + e.getMessage(), e);
        }
    }
    

    public void borrar(Integer codCuenta, Integer codClientePersona) {
        try {
            log.info("Intentando eliminar la cuenta intervinientes con id: {}-{}", codCuenta, codClientePersona);
            Optional<CuentaIntervinientes> credito = getById(codCuenta, codClientePersona);
            if (credito.isPresent()) {
                log.info("Cuenta intervinientes encontrada. Eliminando...");
                this.cuentaIntervinientesRepository.delete(credito.get());
            } else {
                throw new RuntimeException(
                        "La cuenta Intervinientes con id" + codCuenta + "-" + codClientePersona + " no existe");
            }
        } catch (Exception e) {
            throw new CreacionException(
                    "Ocurrio un error al eliminar la cuenta intervinientes, error: " + e.getMessage(), e);
        }
    }

}
