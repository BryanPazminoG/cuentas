package com.banquito.core.baking.cuenta.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.baking.cuenta.dao.CuentaIntervinientesRepository;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientes;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientesPK;
import com.banquito.core.baking.cuenta.dto.CuentaIntervinientesDTO;

import com.banquito.core.baking.cuenta.mappers.CuentaIntervinienteMapper;
import com.banquito.core.baking.cuenta.service.exeption.CreacionException;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/* */
@Service
@Slf4j
public class CuentaIntervinientesService {

    private final CuentaIntervinientesRepository cuentaIntervinientesRepository;

    public CuentaIntervinientesService(CuentaIntervinientesRepository cuentaIntervinientesRepository) {
        this.cuentaIntervinientesRepository = cuentaIntervinientesRepository;

    }

    public CuentaIntervinientesDTO BuscarPorId(Integer codCuenta, String codCliente) {
        CuentaIntervinientesPK cuentaIntervinientePK = new CuentaIntervinientesPK(codCuenta, codCliente);
        Optional<CuentaIntervinientes> cuentaIntervinientes = cuentaIntervinientesRepository
                .findById(cuentaIntervinientePK);
        if (cuentaIntervinientes.isPresent()) {
            log.info("Se encontro la cuenta {} con el codigo cliente {}", codCuenta, codCliente);
            return CuentaIntervinienteMapper.mapper.toDTO(cuentaIntervinientes.get());
        } else {
            log.error("No existe la cuenta {} con el codigo cliente {}", codCuenta, codCliente);
            throw new RuntimeException(
                    "El interviniente" + codCliente + " en la cuenta " + codCuenta + " no existe");
        }
    }

    public List<CuentaIntervinientesDTO> BuscarPorCuenta(Integer codCuenta) {
        List<CuentaIntervinientesDTO> listDTO = new ArrayList<>();
        List<CuentaIntervinientes> listCuentaIntervinientes = this.cuentaIntervinientesRepository
                .findByPKCodCuenta(codCuenta);
        ;
        for (CuentaIntervinientes cuentaIntervinientes : listCuentaIntervinientes) {
            listDTO.add(CuentaIntervinienteMapper.mapper.toDTO(cuentaIntervinientes));
        }
        log.info("Se encontro las cuentas del interviniente: {}", codCuenta);
        return listDTO;
    }

    public List<CuentaIntervinientesDTO> BuscarPorCliente(String CodCliente) {
        List<CuentaIntervinientesDTO> listDTO = new ArrayList<>();
        List<CuentaIntervinientes> listCuentaIntervinientes = this.cuentaIntervinientesRepository
                .findByPKCodCliente(CodCliente);
        ;
        for (CuentaIntervinientes cuentaIntervinientes : listCuentaIntervinientes) {
            listDTO.add(CuentaIntervinienteMapper.mapper.toDTO(cuentaIntervinientes));
        }
        log.info("Se encontro los intervinientes de la cuenta: {}", CodCliente);
        return listDTO;
    }

    public List<CuentaIntervinientesDTO> ListarPorEstado(String estado) {
        if ("ACT".equals(estado) || "INA".equals(estado) || "RES".equals(estado) || "REV".equals(estado)
                || "SUS".equals(estado) || "LIB".equals(estado) || "CES".equals(estado)) {
            List<CuentaIntervinientesDTO> listDTO = new ArrayList<>();
            for (CuentaIntervinientes cuentaIntervinientes : this.cuentaIntervinientesRepository.findByEstado(estado)) {
                listDTO.add(CuentaIntervinienteMapper.mapper.toDTO(cuentaIntervinientes));
            }
            log.info("Se encontro los intervinientes con el estado: {}", estado);
            return listDTO;
        } else {
            log.error("El estado {} es invalido", estado);
            throw new RuntimeException("Estado ingresado invalido: " + estado);
        }
    }

    @Transactional
    public CuentaIntervinientesDTO Crear(CuentaIntervinientesDTO dto) {
        try {
            CuentaIntervinientes cuentaIntervinientes = CuentaIntervinienteMapper.mapper.toEntity(dto);
            LocalDateTime fechaActualTimestamp = LocalDateTime.now();
            cuentaIntervinientes.setFechaInicio(Timestamp.valueOf(fechaActualTimestamp));
            cuentaIntervinientes.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
            dto = CuentaIntervinienteMapper.mapper
                    .toDTO(this.cuentaIntervinientesRepository.save(cuentaIntervinientes));
            log.info("Se creo la cuenta interviniente: {}", dto);
            return dto;
        } catch (Exception e) {
            throw new CreacionException(
                    "Error en creacion de los Intervinientes en la cuenta: " + dto + ", Error: " + e, e);
        }
    }

    @Transactional
    public CuentaIntervinientesDTO Actualizar(CuentaIntervinientesDTO dto) {
        try {
            CuentaIntervinientesPK PK = new CuentaIntervinientesPK(dto.getCodCuenta(), dto.getCodCliente());
            Optional<CuentaIntervinientes> cuentaIntervinientes = cuentaIntervinientesRepository.findById(PK);

            if (cuentaIntervinientes.isPresent()) {
                cuentaIntervinientes = Optional.ofNullable(CuentaIntervinienteMapper.mapper.toEntity(dto));
                LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                cuentaIntervinientes.get().setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                dto = CuentaIntervinienteMapper.mapper
                        .toDTO(this.cuentaIntervinientesRepository.save(cuentaIntervinientes.get()));
                log.info("La cuenta intervinientes {} se actualizo correctamente", dto);
                return dto;
            } else {
                log.error("No existe la cuenta intervinientes con cod_cuenta: {} y cod_cliente {}", dto.getCodCuenta(),
                        dto.getCodCliente());
                throw new RuntimeException(
                        "La cuenta Intervinientes con id cuenta: " + dto.getCodCuenta() + ", id cliente: "
                                + dto.getCodCliente() + " no existe");
            }
        } catch (Exception e) {
            log.error("Error al actualizar la cuenta interviente");
            throw new CreacionException(
                    "Ocurrió un error al actualizar la cuenta interviniente, error: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void Eliminar(Integer codCuenta, String codCliente) {
        try {
            CuentaIntervinientesPK PK = new CuentaIntervinientesPK(codCuenta, codCliente);
            Optional<CuentaIntervinientes> cuentaInteviniente = cuentaIntervinientesRepository.findById(PK);
            if (cuentaInteviniente.isPresent()) {
                log.info("Cuenta intervinientes encontrada. Eliminando...");
                this.cuentaIntervinientesRepository.delete(cuentaInteviniente.get());
                log.info("Cuenta intervinientes Eliminada");
            } else {
                log.error("La cuenta Intervinientes con id {} - {} no existe", codCuenta, codCliente);
                throw new RuntimeException(
                        "La cuenta Intervinientes con id" + codCuenta + "-" + codCliente + " no existe");
            }
        } catch (Exception e) {
            throw new CreacionException(
                    "Ocurrio un error al eliminar la cuenta intervinientes, error: " + e.getMessage(), e);
        }
    }

}