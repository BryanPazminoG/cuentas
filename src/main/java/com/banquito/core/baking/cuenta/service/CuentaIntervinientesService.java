package com.banquito.core.baking.cuenta.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.baking.cuenta.dao.CuentaIntervinientesRepository;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientes;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientesPK;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CuentaIntervinientesService {

    private final CuentaIntervinientesRepository cuentaIntervinientesRepository;

    public CuentaIntervinientesService(CuentaIntervinientesRepository cuentaIntervinientesRepository) {
        this.cuentaIntervinientesRepository = cuentaIntervinientesRepository;

    }

    public Optional<CuentaIntervinientes> getById(Integer codCuenta, String codClientePersona) {
        CuentaIntervinientesPK cuentaIntervinientePK = new CuentaIntervinientesPK(codCuenta, codClientePersona);
        log.info("Se encontro la cuenta {} con el codigo cliente {}", codCuenta, codClientePersona);
        return this.cuentaIntervinientesRepository.findById(cuentaIntervinientePK);
    }

    public Iterable<CuentaIntervinientes> getByCuenta(Integer codCuenta) {
        log.info("Se encontro la cuenta: {}", codCuenta);
        return this.cuentaIntervinientesRepository.findByPKCodCuenta(codCuenta);
    }

    public Iterable<CuentaIntervinientes> getByCodCliente(String CodClientePersona) {
        log.info("Se encontro el cliente {}", CodClientePersona);
        return this.cuentaIntervinientesRepository.findByPKCodClientePersona(CodClientePersona);
    }

    // public List<Cuenta> getCuentaByInter(Integer CodClientePersona) {
    // log.info("Va a obtener las cuentas del cliente:{}", CodClientePersona);
    // try {
    // Iterable<CuentaIntervinientes> listaIntervinientes =
    // getByCodCliente(CodClientePersona);
    // List<Cuenta> listaCuentas = new ArrayList<>();
    // for (CuentaIntervinientes intervinientes : listaIntervinientes) {
    // Optional<Cuenta> cuenta =
    // this.cuentaRepository.findById(intervinientes.getPK().getCodCuenta());
    // if (cuenta.isPresent()) {
    // log.debug("Cuenta obtenida: {}",cuenta.get());
    // listaCuentas.add(cuenta.get());
    // }
    // }
    // return listaCuentas;

    // } catch (Exception e) {
    // throw new CreacionException(
    // "Error al buscar las cuentas del cliente con codigo: " + CodClientePersona +
    // ", Error: " + e,
    // e);
    // }
    // }

    @Transactional
    public void crear(CuentaIntervinientes dto) {
        try {
            dto.setEstado("ACT");
            dto.setFechaUltimoCambio(new Date());

            this.cuentaIntervinientesRepository.save(dto);
            log.info("Se creo la cuenta intervinientes: {}", dto);
        } catch (Exception e) {
            throw new CreacionException(
                    "Error en creacion de los Intervinientes en la cuenta: " + dto + ", Error: " + e, e);
        }
    }

    public void actualizar(CuentaIntervinientes cuentaIntervinientesdto) {
        try {
            Optional<CuentaIntervinientes> cuentaIntervinientesOptional = getById(
                    cuentaIntervinientesdto.getPK().getCodCuenta(),
                    cuentaIntervinientesdto.getPK().getCodClientePersona());

            if (cuentaIntervinientesOptional.isPresent()) {
                cuentaIntervinientesdto.setFechaUltimoCambio(new Date());
                cuentaIntervinientesdto.setEstado(cuentaIntervinientesOptional.get().getEstado());
                this.cuentaIntervinientesRepository.save(cuentaIntervinientesdto);
                log.info("La cuenta intervinientes {} se actualizo correctamente", cuentaIntervinientesdto);
            } else {
                log.error(
                        "La cuenta Intervinientes con id cuenta: " + cuentaIntervinientesdto.getPK().getCodCuenta() + ", id cliente: "
                                + cuentaIntervinientesdto.getPK().getCodClientePersona() + " no existe");
            }
        } catch (Exception e) {
            throw new CreacionException("Ocurrió un error al actualizar la cuenta, error: " + e.getMessage(), e);
        }
    }
    

    public void borrar(Integer codCuenta, String codClientePersona) {
        try {
            log.info("Intentando eliminar la cuenta intervinientes con id: {}-{}", codCuenta, codClientePersona);
            Optional<CuentaIntervinientes> cuentaInteviniente = getById(codCuenta, codClientePersona);
            if (cuentaInteviniente.isPresent()) {
                log.info("Cuenta intervinientes encontrada. Eliminando...");
                this.cuentaIntervinientesRepository.delete(cuentaInteviniente.get());
            } else {
                log.error(
                        "La cuenta Intervinientes con id" + codCuenta + "-" + codClientePersona + " no existe");
            }
        } catch (Exception e) {
            throw new CreacionException(
                    "Ocurrio un error al eliminar la cuenta intervinientes, error: " + e.getMessage(), e);
        }
    }
}