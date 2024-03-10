package com.banquito.core.baking.cuenta.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.banquito.core.baking.cuenta.dao.CuentaRepository;
import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.dto.CuentaDTO;
import com.banquito.core.baking.cuenta.dto.Builder.CuentaBuilder;
import com.banquito.core.baking.cuenta.service.exeption.CreacionException;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public List<CuentaDTO> Listar() {
        log.info("Se va a obtener todos los tipos de cuentas");
        List<CuentaDTO> dtos = new ArrayList<>();
        for (Cuenta cuenta : this.cuentaRepository.findAll()) {
            dtos.add(CuentaBuilder.toDTO(cuenta));
        }
        return dtos;
    }

    public CuentaDTO BuscarPorId(Integer codCuenta) {
        log.info("Obteniendo la Cuenta con ID: {}", codCuenta);
        Optional<Cuenta> optCuenta = this.cuentaRepository.findById(codCuenta);
        if (optCuenta.isPresent()) {
            log.info("Cuenta obtenida: {}", optCuenta.get());
            return CuentaBuilder.toDTO(optCuenta.get());
        } else {
            throw new RuntimeException("No se encontro la cuenta con ID: " + codCuenta);
        }
    }

    @Transactional
    public CuentaDTO Crear(CuentaDTO dto) {
        try {
            Cuenta cuenta = CuentaBuilder.toCuenta(dto);

            LocalDateTime fechaActualTimestamp = LocalDateTime.now();
            cuenta.setFechaCreacion(Timestamp.valueOf(fechaActualTimestamp));
            cuenta.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
            cuenta.setEstado("ACT");
            cuenta.setFechaActivacion(Timestamp.valueOf(fechaActualTimestamp));

            CuentaDTO CuentaDTO = CuentaBuilder.toDTO(cuentaRepository.save(cuenta));
            log.info("Se creo la cuenta: {}", cuenta);
            return CuentaDTO;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la cuenta.", e);
        }
    }

    @Transactional
    public void Actualizar(CuentaDTO dto) {
        try {
            Cuenta cuentaAux = this.cuentaRepository.findById(dto.getCodCuenta()).get();
            if ("ACT".equals(cuentaAux.getEstado())) {
                Cuenta cuentaTmp = CuentaBuilder.toCuenta(dto);
                Cuenta cuenta = CuentaBuilder.copyCuenta(cuentaTmp, cuentaAux);
                cuenta.setEstado("ACT");
                this.cuentaRepository.save(cuenta);
                log.info("Se actualizaron los datos de la cuenta: {}", cuenta);
            } else {
                log.error("No se puede actualizar, cuenta: {} se encuentra INACTIVO", cuentaAux);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el cliente.", e);
        }
    }

    @Transactional
    public void Eliminar(Integer id) {
        try {
            Optional<Cuenta> cuenta = this.cuentaRepository.findById(id);
            if (cuenta.isPresent()) {
                this.cuentaRepository.delete(cuenta.get());
                log.info("Se elimino con exito la cuenta: {}", cuenta);
            } else {
                throw new RuntimeException("La cuenta con ID: " + id + " no existe");
            }
        } catch (Exception e) {
            throw new CreacionException("Ocurrio un error al eliminar la cuenta, error: " + e.getMessage(), e);
        }
    }

    public CuentaDTO obtenerPorNumeroCuenta(String numeroCuenta) {
        log.info("Obteniendo la Cuenta: {}", numeroCuenta);
        Cuenta cuenta = this.cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (cuenta != null) {
            log.info("Cuenta obtenida: {}", cuenta);
            return CuentaBuilder.toDTO(cuenta);
        } else {
            throw new RuntimeException("No se encontro la cuenta: " + numeroCuenta);
        }
    }

    @Transactional
    public void actualizarBalance(CuentaDTO dto) {
        try {
            Cuenta cuentaAux = this.cuentaRepository.findById(dto.getCodCuenta()).get();
            if (cuentaAux != null) {
                Cuenta cuentaTmp = CuentaBuilder.toCuenta(dto);
                Cuenta cuenta = CuentaBuilder.copyCuenta(cuentaTmp, cuentaAux);
                cuenta.setSaldoDisponible(cuentaAux.getSaldoDisponible().add(cuenta.getSaldoDisponible()));
                cuenta.setSaldoContable(cuentaAux.getSaldoContable().add(cuenta.getSaldoContable()));
                this.cuentaRepository.save(cuenta);
                log.info("Se actualizo el balance de la cuenta: {}", cuenta);
            } else {
                throw new RuntimeException("La cuenta con ID: " + dto.getCodCuenta() + " no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el balance de la cuenta.", e);
        }
    }

    public List<CuentaDTO> obtenerCuentasCliente(String codCliente) {
        try {
            List<Cuenta> cuentas = this.cuentaRepository.findByCodCliente(codCliente);
            if (!cuentas.isEmpty()) {
                List<CuentaDTO> cuentasDTO = new ArrayList<>();
                for (Cuenta cuenta : cuentas) {
                    cuentasDTO.add(CuentaBuilder.toDTO(cuenta));
                }
                return cuentasDTO;
            } else {
                throw new RuntimeException("El cliente no tiene cuentas asociadas.");
            }
        } catch (Exception e) {
            throw new CreacionException("Ocurrió un error al obtener cuentas del cliente " + e.getMessage(), e);
        }
    }

}
