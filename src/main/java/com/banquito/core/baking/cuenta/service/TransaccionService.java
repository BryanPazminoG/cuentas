package com.banquito.core.baking.cuenta.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.banquito.core.baking.cuenta.dao.CuentaRepository;
import com.banquito.core.baking.cuenta.dao.TransaccionRepository;
import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.domain.Transaccion;
import com.banquito.core.baking.cuenta.dto.TransaccionDTO;
import com.banquito.core.baking.cuenta.dto.Builder.TransaccionBuilder;
import com.banquito.core.baking.cuenta.service.exeption.CreacionException;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TransaccionService {
    private final TransaccionRepository transaccionRepository;
    private final CuentaRepository cuentaRepository;

    public TransaccionService(TransaccionRepository transaccionRepository, CuentaRepository cuentaRepository) {
        this.transaccionRepository = transaccionRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public TransaccionDTO buscarPorId(Integer codTransaccion) {
        log.info("Obteniendo la transaccion con ID: {}", codTransaccion);
        Optional<Transaccion> optTransaccion = this.transaccionRepository.findById(codTransaccion);
        if (optTransaccion.isPresent()) {
            log.info("Transaccion obtenida: {}", optTransaccion.get());
            return TransaccionBuilder.toDTO(optTransaccion.get());
        } else {
            log.error("No se encontró transacción: {}", codTransaccion);
            throw new RuntimeException("No se encontro la transaccion ID: " + codTransaccion);
        }
    }

    @Transactional
    public TransaccionDTO crear(TransaccionDTO dto) {
        try {
            Transaccion transaccion = TransaccionBuilder.toTransaccion(dto);
            LocalDateTime fechaActualTimestamp = LocalDateTime.now();

            transaccion.setCodUnico(new DigestUtils("MD2").digestAsHex(dto.toString()));
            transaccion.setFechaCreacion(Timestamp.valueOf(fechaActualTimestamp));
            transaccion.setFechaAfectacion(Timestamp.valueOf(fechaActualTimestamp));
            transaccion.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));

            dto = TransaccionBuilder.toDTO(this.transaccionRepository.save(transaccion));
            log.info("Se creo la transaccion: {}", transaccion);
            return dto;
        } catch (Exception e) {
            throw new CreacionException("Error al crear la transaccion: ", e);
        }
    }

    @Transactional
    public void depositar(TransaccionDTO dto) {
        try {
            log.info("Iniciando el proceso de deposito en la cuenta: {}", dto.getCodCuenta());

            Optional<Cuenta> optCuenta = this.cuentaRepository.findById(dto.getCodCuenta());
            if (optCuenta.isPresent()) {
                Cuenta cuenta = optCuenta.get();
                if ("ACT".equals(cuenta.getEstado())) {
                    if (cuenta.getSaldoDisponible().compareTo(dto.getValorDebe()) > 0) {
                        LocalDateTime fechaActualTimestamp = LocalDateTime.now();

                        cuenta.setSaldoContable(cuenta.getSaldoContable().add(dto.getValorDebe()));
                        cuenta.setSaldoDisponible(cuenta.getSaldoDisponible().add(dto.getValorDebe()));
                        cuenta.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));

                        dto.setCodUnico(new DigestUtils("MD2").digestAsHex(dto.toString()));
                        dto.setTipoAfectacion("C");
                        dto.setValorHaber(BigDecimal.ZERO);
                        dto.setTipoTransaccion("DEP");
                        dto.setDetalle("DEPOSITO BANCARIO");
                        dto.setEstado("EXI");

                        this.cuentaRepository.save(cuenta);
                        this.crear(dto);
                        log.info("Deposito realizado con exito");
                    } else {
                        log.error("No posee fondos suficientes.");
                        throw new RuntimeException("Fondos insuficientes");
                    }
                } else {
                    log.error("La cuenta {} no se encuentra ACTIVA", cuenta.getNumeroCuenta());
                    throw new RuntimeException("La cuenta no esta ACTIVA: " + cuenta.getNumeroCuenta());
                }
            } else {
                log.error("No existe la cuenta: {}", dto.getCodCuenta());
                throw new RuntimeException("No se encontro la cuenta: " + dto.getCodCuenta());
            }
        } catch (Exception e) {
            throw new CreacionException("Error en creacion de la transaccion:" + dto + ". Error: " + e, e);
        }
    }

    @Transactional
    public void retirar(TransaccionDTO dto) {
        try {
            log.info("Iniciando el proceso de retiro en la cuenta: {}", dto.getCodCuenta());

            Optional<Cuenta> optCuenta = this.cuentaRepository.findById(dto.getCodCuenta());
            if (optCuenta.isPresent()) {
                Cuenta cuenta = optCuenta.get();
                if ("ACT".equals(cuenta.getEstado())) {
                    if (cuenta.getSaldoDisponible().compareTo(dto.getValorHaber()) > 0) {
                        LocalDateTime fechaActualTimestamp = LocalDateTime.now();

                        cuenta.setSaldoContable(cuenta.getSaldoContable().subtract(dto.getValorHaber()));
                        cuenta.setSaldoDisponible(cuenta.getSaldoDisponible().subtract(dto.getValorHaber()));
                        cuenta.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));

                        dto.setCodUnico(new DigestUtils("MD2").digestAsHex(dto.toString()));
                        dto.setTipoAfectacion("D");
                        dto.setValorDebe(BigDecimal.ZERO);
                        dto.setTipoTransaccion("RET");
                        dto.setDetalle("RETIRO");
                        dto.setEstado("EXI");

                        this.cuentaRepository.save(cuenta);
                        this.crear(dto);
                        log.info("Retiro realizado con exito.");
                    } else {
                        log.error("No posee fondos suficientes.");
                        throw new RuntimeException("Fondos insuficientes");
                    }
                } else {
                    log.error("La cuenta {} no se encuentra ACTIVA", cuenta.getNumeroCuenta());
                    throw new RuntimeException("La cuenta no esta ACTIVA: " + cuenta.getNumeroCuenta());
                }
            } else {
                log.error("No existe la cuenta: {}", dto.getCodCuenta());
                throw new RuntimeException("No se encontro la cuenta: " + dto.getCodCuenta());
            }
        } catch (Exception e) {
            throw new CreacionException("Error en creacion de la transaccion: " + dto + ". Error: " + e, e);
        }
    }

    @Transactional
    public void transferir(TransaccionDTO dto, BigDecimal monto) {
        try {
            log.info("Iniciando el proceso de transferencia con la transaccion: {}", dto);
            Optional<Cuenta> optCuenta = this.cuentaRepository.findById(dto.getCodCuenta());
            if (optCuenta.isPresent()) {
                Cuenta cuenta = optCuenta.get();
                if ("ACT".equals(cuenta.getEstado())) {
                    if (cuenta.getSaldoDisponible().compareTo(monto) >= 0) {
                        LocalDateTime fechaActualTimestamp = LocalDateTime.now();

                        dto.setCodUnico(new DigestUtils("MD2").digestAsHex(dto.toString()));
                        dto.setEstado("EXI");
                        dto.setCanal("BWE");
                        dto.setTipoTransaccion("TRA");

                        if ("D".equals(dto.getTipoAfectacion())) {
                            cuenta.setSaldoContable(cuenta.getSaldoContable().subtract(monto));
                            cuenta.setSaldoDisponible(cuenta.getSaldoDisponible().subtract(monto));
                            cuenta.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                            dto.setValorDebe(BigDecimal.ZERO);
                            dto.setValorHaber(monto);
                        } else {
                            cuenta.setSaldoContable(cuenta.getSaldoContable().add(monto));
                            cuenta.setSaldoDisponible(cuenta.getSaldoDisponible().add(monto));
                            cuenta.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                            dto.setValorDebe(monto);
                            dto.setValorHaber(BigDecimal.ZERO);
                        }

                        this.cuentaRepository.save(cuenta);
                        this.crear(dto);

                        log.info("Transferencia realizada con exito. Cuenta: {}, Monto: {}",
                                cuenta.getNumeroCuenta(), monto);
                    } else {
                        log.error("Saldo insuficiente en Cuenta: {}", cuenta.getNumeroCuenta());
                        throw new RuntimeException("El saldo es insuficiente en cuenta: " + cuenta.getNumeroCuenta());
                    }
                } else {
                    log.error("La cuenta {} no se encuentra ACTIVA", cuenta.getNumeroCuenta());
                    throw new RuntimeException("La cuenta no esta ACTIVA: " + cuenta.getNumeroCuenta());
                }
            } else {
                log.error("No existe la cuenta: {}", dto.getCodCuenta());
                throw new RuntimeException("No se encontro la cuenta: " + dto.getCodCuenta());
            }
        } catch (Exception e) {
            throw new CreacionException("Error en creacion de la transaccion: " + dto + ", Error: " + e, e);
        }
    }

    public List<TransaccionDTO> buscarPorCodigoCuenta(Integer codCuenta) {
        log.info("Buscando transacciones por codigo de cuenta: {}", codCuenta);
        List<TransaccionDTO> dtos = new ArrayList<>();
        if(!this.transaccionRepository.findByCodCuenta(codCuenta).isEmpty()) {
            for (Transaccion transaccion : this.transaccionRepository.findByCodCuenta(codCuenta)) {
                dtos.add(TransaccionBuilder.toDTO(transaccion));
            }            
        } else {
            log.error("No existen transacciones en la cuenta: {}", codCuenta);
        }
        return dtos;
    }
}
