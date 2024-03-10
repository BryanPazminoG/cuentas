package com.banquito.core.baking.cuenta.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.baking.cuenta.dao.CuentaIntervinientesRepository;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientes;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientesPK;
import com.banquito.core.baking.cuenta.dto.CuentaIntervinientesDTO;
import com.banquito.core.baking.cuenta.mappers.CuentaIntervinienteMapper;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CuentaIntervinientesService {

    private final CuentaIntervinientesRepository cuentaIntervinientesRepository;

    public CuentaIntervinientesService(CuentaIntervinientesRepository cuentaIntervinientesRepository) {
        this.cuentaIntervinientesRepository = cuentaIntervinientesRepository;

    }

    public CuentaIntervinientesDTO BuscarPorId(Integer codCuenta, Integer codClientePersona) {
        CuentaIntervinientesPK cuentaIntervinientePK = new CuentaIntervinientesPK(codCuenta, codClientePersona);
        Optional<CuentaIntervinientes> cuentaIntervinientes = cuentaIntervinientesRepository.findById(cuentaIntervinientePK);
        if(cuentaIntervinientes.isPresent()){
            log.info("Se encontro la cuenta {} con el codigo cliente {}", codCuenta, codClientePersona);
            return CuentaIntervinienteMapper.INSTANCE.toDTO(cuentaIntervinientes.get());
        }else{
            throw new RuntimeException("El interviniente" + codClientePersona + " en la cuenta " + codCuenta + " no existe");
        }
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
    public CuentaIntervinientesDTO Crear(CuentaIntervinientesDTO dto) {
        try {
            CuentaIntervinientes cuentaIntervinientes = CuentaIntervinienteMapper.INSTANCE.toEntity(dto);

            cuentaIntervinientes.setEstado("ACT");
            LocalDateTime fechaActualTimestamp = LocalDateTime.now();
            cuentaIntervinientes.setFechaInicio(Timestamp.valueOf(fechaActualTimestamp));
            cuentaIntervinientes.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));

            log.info("Se creara la cuenta interviniente: {}", cuentaIntervinientes);
            return CuentaIntervinienteMapper.INSTANCE.toDTO(this.cuentaIntervinientesRepository.save(cuentaIntervinientes));
        } catch (Exception e) {
            throw new CreacionException(
                    "Error en creacion de los Intervinientes en la cuenta: " + dto + ", Error: " + e, e);
        }
    }

    @Transactional
    public CuentaIntervinientesDTO Actualizar(CuentaIntervinientesDTO dto) {
        try {
            CuentaIntervinientesPK PK = new CuentaIntervinientesPK(dto.getCodCuenta(), dto.getCodClientePersona());
            Optional<CuentaIntervinientes> cuentaIntervinientes = cuentaIntervinientesRepository.findById(PK);

            if (cuentaIntervinientes.isPresent()) {
                cuentaIntervinientes = Optional.ofNullable(CuentaIntervinienteMapper.INSTANCE.toEntity(dto));
                LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                cuentaIntervinientes.get().setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                log.info("La cuenta intervinientes {} se actualizara", cuentaIntervinientes.get());
                return CuentaIntervinienteMapper.INSTANCE.toDTO(this.cuentaIntervinientesRepository.save(cuentaIntervinientes.get()));
            } else {
                throw new RuntimeException("La cuenta Intervinientes con id cuenta: " + dto.getCodCuenta() + ", id cliente: "
                + dto.getCodClientePersona() + " no existe");
            }
        } catch (Exception e) {
            throw new CreacionException("Ocurrió un error al actualizar la cuenta, error: " + e.getMessage(), e);
        }
    }
    

    public void Eliminar(Integer codCuenta, Integer codClientePersona) {
        try {
            log.info("Intentando eliminar la cuenta intervinientes con id: {}-{}", codCuenta, codClientePersona);
            CuentaIntervinientesPK PK = new CuentaIntervinientesPK(codCuenta, codClientePersona);
            Optional<CuentaIntervinientes> cuentaInteviniente = cuentaIntervinientesRepository.findById(PK);
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