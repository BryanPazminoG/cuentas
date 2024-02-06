package com.banquito.core.baking.cuenta.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.baking.cuenta.dao.CuentaRepository;
import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.dto.CuentaBuilder;
import com.banquito.core.baking.cuenta.dto.CuentaDTO;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public CuentaDTO obtenerPorId(Integer codCuenta) {
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
    public void crear(CuentaDTO dto) {
        try {
            Cuenta cuenta = CuentaBuilder.toCuenta(dto);
            cuenta.setFechaCreacion(new Date());
            this.cuentaRepository.save(cuenta);
            log.info("Se creo la cuenta: {}", cuenta);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la cuenta.", e);
        }
    }

    @Transactional
    public void actualizar(CuentaDTO dto) {
        try {
            Cuenta cuentaAux = this.cuentaRepository.findById(dto.getCodCuenta()).get();
            Cuenta cuentaTmp = CuentaBuilder.toCuenta(dto);
            Cuenta cuenta = CuentaBuilder.copyCuenta(cuentaTmp, cuentaAux);
            cuenta.setFechaUltimoCambio(new Date());
            this.cuentaRepository.save(cuenta);
            log.info("Se actualizaron los datos de la cuenta: {}", cuenta);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la cuenta.", e);
        }
    }

    @Transactional
    public void eliminar(Integer id) {
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

    public List<CuentaDTO> obtenerCuentasCliente(Integer codCliente) {
        log.info("Obteniendo cuentas de cliente: {}", codCliente);
        List<CuentaDTO> dtos = new ArrayList<>();
        List<Cuenta> cuentas = this.cuentaRepository.findByCodCliente(codCliente);

        if (cuentas != null && !cuentas.isEmpty()) {
            for (Cuenta cuenta : this.cuentaRepository.findByCodCliente(codCliente)) {
                dtos.add(CuentaBuilder.toDTO(cuenta));
            }
            return dtos;
        } else {
            throw new RuntimeException("El cliente no tiene cuentas asociadas.");
        }
    }
}
