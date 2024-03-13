package com.banquito.core.baking.cuenta.service;

import java.math.BigDecimal;
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
import org.apache.commons.codec.digest.DigestUtils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public CuentaDTO BuscarPorId(Integer codCuenta) {
        Optional<Cuenta> cuenta = this.cuentaRepository.findById(codCuenta);
        if (cuenta.isPresent()) {
            log.info("Cuenta con ID: {} encontrada", codCuenta);
            CuentaDTO dto = CuentaBuilder.toDTO(cuenta.get());
            return dto;
        } else {
            log.error("La cuenta con ID {} no existe", codCuenta);
            throw new RuntimeException("No se encontro la cuenta");
        }
    }

    public CuentaDTO BuscarPorNumeroCuenta(String numeroCuenta) {
        Optional<Cuenta> cuenta = this.cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (cuenta.isPresent()) {
            log.info("Cuenta con el numero de cuenta: {} encontrada", cuenta);
            CuentaDTO dto = CuentaBuilder.toDTO(cuenta.get());
            return dto;
        } else {
            log.error("La cuenta con el numero de cuenta {} no existe", numeroCuenta);
            throw new RuntimeException("No se encontro la cuenta: ");
        }
    }

    public List<CuentaDTO> BuscarPorCliente(String codCliente) {
        
        List<CuentaDTO> listDTO = new ArrayList<>();
        List<Cuenta> cuentas = this.cuentaRepository.findByCodClienteOrderByFechaCreacion(codCliente);

        for (Cuenta cuenta : cuentas) {
            listDTO.add(CuentaBuilder.toDTO(cuenta));
        }
        log.info("Se encontro el listando de las cuentas del cliente: {}", codCliente);
        return listDTO;
    }

    @Transactional
    public CuentaDTO Crear(CuentaDTO dto) {
        try {
            Cuenta cuenta = CuentaBuilder.toCuenta(dto);

            LocalDateTime fechaActualTimestamp = LocalDateTime.now();            
            String banco = "1234567890";
            String numeroCuenta = "";
            while (true) {
                numeroCuenta = "";
                for (int x = 0; x < 10; x++) {
                    numeroCuenta += banco.charAt((int)(Math.random()*(9-0+1)+0));
                }
                Optional<Cuenta> cuentaValidacion = this.cuentaRepository.findByNumeroCuenta(numeroCuenta);
                if(!cuentaValidacion.isPresent()) break;
            }
            
            cuenta.setNumeroCuenta(numeroCuenta);
            cuenta.setSaldoContable(new BigDecimal("0"));
            cuenta.setSaldoDisponible(new BigDecimal("0"));
            cuenta.setFechaCreacion(Timestamp.valueOf(fechaActualTimestamp));
            cuenta.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
            cuenta.setEstado("ACT");
            cuenta.setFechaActivacion(Timestamp.valueOf(fechaActualTimestamp));
            cuenta.setCodUnico(new DigestUtils("MD2").digestAsHex(cuenta.toString()));

            CuentaDTO CuentaDTO = CuentaBuilder.toDTO(cuentaRepository.save(cuenta));
            log.info("La cuenta: {} se ha creado Exitosamente", CuentaDTO.getCodCuenta());

            return CuentaDTO;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la cuenta.", e);
        }
    }

    @Transactional
    public CuentaDTO Actualizar(CuentaDTO dto) {
        try {
            Optional<Cuenta> cuenta = this.cuentaRepository.findById(dto.getCodCuenta());

            if (cuenta.isPresent()) {
                Cuenta cuentaSource = CuentaBuilder.toCuenta(dto);
                Cuenta cuentaDestiny = CuentaBuilder.copyCuenta(cuentaSource, cuenta.get());

                LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                cuentaDestiny.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                dto = CuentaBuilder.toDTO(this.cuentaRepository.save(cuentaDestiny));
                log.info("Se actualizaron los datos de la cuenta: {} Exitosamente", cuentaDestiny);
                return dto;
            } else {
                log.error("No existe la cuenta con el ID: {}", dto.getCodCuenta());
                throw new RuntimeException("La cuenta no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el cuenta.", e);
        }
    }

    @Transactional
    public void ActualizarBalance(CuentaDTO dto) {
        try {
            Optional<Cuenta> cuenta = this.cuentaRepository.findById(dto.getCodCuenta());
            if (cuenta.isPresent()) {
                cuenta.get().setSaldoDisponible(cuenta.get().getSaldoDisponible().add(dto.getSaldoDisponible()));
                cuenta.get().setSaldoContable(cuenta.get().getSaldoContable().add(dto.getSaldoContable()));
                this.cuentaRepository.save(cuenta.get());
                log.info("Se actualizo el balance de la cuenta: {}", cuenta.get().getCodCuenta());
            } else {
                log.error("No existe la cuenta con el ID: {}", dto.getCodCuenta());
                throw new RuntimeException("La cuenta con ID: " + dto.getCodCuenta() + " no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el balance de la cuenta.", e);
        }
    }
}
