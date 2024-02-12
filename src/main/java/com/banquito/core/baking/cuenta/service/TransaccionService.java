package com.banquito.core.baking.cuenta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import org.apache.commons.codec.digest.DigestUtils;

import com.banquito.core.baking.cuenta.dao.CuentaRepository;
import com.banquito.core.baking.cuenta.dao.TransaccionRepository;
import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.domain.Transaccion;
import com.banquito.core.baking.cuenta.dto.TransaccionBuilder;
import com.banquito.core.baking.cuenta.dto.TransaccionDTO;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransaccionService {
    private final TransaccionRepository transaccionRepository;
    private final CuentaRepository cuentaRepository;

    public TransaccionService(TransaccionRepository transaccionRepository, CuentaRepository cuentaRepository) {
        this.transaccionRepository = transaccionRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public TransaccionDTO obtenerPorId(Integer codTransaccion) {
        log.info("Obteniendo la transaccion con ID: {}", codTransaccion);
        Optional<Transaccion> optTransaccion = this.transaccionRepository.findById(codTransaccion);
        if (optTransaccion.isPresent()) {
            log.info("Transaccion obtenida: {}", optTransaccion.get());
            return TransaccionBuilder.toDTO(optTransaccion.get());
        } else {
            throw new RuntimeException("No se encontro la transaccion ID: " + codTransaccion);
        }
    }

    @Transactional
    public void crear(TransaccionDTO dto) {
        try {
            Transaccion transaccion = TransaccionBuilder.toTransaccion(dto);
            transaccion.setCodUnico(new DigestUtils("MD5").digestAsHex(dto.toString()));
            transaccion.setFechaCreacion(new Date());
            transaccion.setFechaUltimoCambio(new Date());
            this.transaccionRepository.save(transaccion);
            log.info("Se creo la transaccion: {}", transaccion);
        } catch (Exception e) {

            throw new CreacionException("Error al crear la transaccion: ", e);
        }
    }

    @Transactional
    public void depositar(String numCuenta, BigDecimal valorDebe) {
        try {
            log.info("Iniciando el proceso de deposito en la cuenta: {}", numCuenta);
            int longitud = 64;
            String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            Random random = new Random();
            StringBuilder codigoUnicoGenerado = new StringBuilder(longitud);
            Transaccion transaccion = new Transaccion();

            if (cuentaRepository.findByNumeroCuenta(numCuenta) != null) {
                Cuenta cuentaBeneficiario = cuentaRepository.findByNumeroCuenta(numCuenta);

                cuentaBeneficiario.setSaldoContable(cuentaBeneficiario.getSaldoContable().add(valorDebe));
                cuentaBeneficiario.setSaldoDisponible(cuentaBeneficiario.getSaldoDisponible().add(valorDebe));
                //cuentaBeneficiario.setFechaUltimoCambio(new Date());

                transaccion.setCodCuentaDestino(cuentaBeneficiario.getCodCuenta());

                for (int i = 0; i < longitud; i++) {
                    int index = random.nextInt(caracteres.length());
                    codigoUnicoGenerado.append(caracteres.charAt(index));
                }
                transaccion.setCodUnico(codigoUnicoGenerado.toString());
                transaccion.setTipoAfectacion("D");
                transaccion.setValorDebe(valorDebe);
                transaccion.setValorHaber(valorDebe.subtract(valorDebe));
                transaccion.setTipoTransaccion("DEP");
                transaccion.setDetalle("DEPOSITO BANCARIO");
                transaccion.setFechaCreacion(new Date());
                transaccion.setEstado("EXI");
                transaccion.setFechaUltimoCambio(new Date());
                transaccion.setVersion(1L);

                cuentaRepository.save(cuentaBeneficiario);
                this.transaccionRepository.save(transaccion);
                log.info("Deposito realizado con exito. Transaccion creada: {}", transaccion);
            } else {
                throw new RuntimeException("No existe el numero de cuenta ingresado");
            }
        } catch (Exception e) {
            throw new CreacionException("Error en creacion de la transaccion:  Error: " + e, e);
        }
    }

    @Transactional
    public void retirar(String numCuenta, BigDecimal valorHaber) {
        try {
            log.info("Iniciando el proceso de retiro en la cuenta: {}", numCuenta);
            int longitud = 64;
            String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            Random random = new Random();
            StringBuilder codigoUnicoGenerado = new StringBuilder(longitud);
            Transaccion transaccion = new Transaccion();

            if (cuentaRepository.findByNumeroCuenta(numCuenta) != null) {
                Cuenta cuentaPropietario = cuentaRepository.findByNumeroCuenta(numCuenta);

                if (cuentaPropietario.getSaldoDisponible().compareTo(valorHaber) > 0) {
                    cuentaPropietario.setSaldoContable(cuentaPropietario.getSaldoContable().subtract(valorHaber));
                    cuentaPropietario.setSaldoDisponible(cuentaPropietario.getSaldoDisponible().subtract(valorHaber));
                    cuentaPropietario.setFechaUltimoCambio(new Date());

                    transaccion.setCodCuentaOrigen(cuentaPropietario.getCodCuenta());

                    for (int i = 0; i < longitud; i++) {
                        int index = random.nextInt(caracteres.length());
                        codigoUnicoGenerado.append(caracteres.charAt(index));
                    }
                    transaccion.setCodUnico(codigoUnicoGenerado.toString());
                    transaccion.setTipoAfectacion("C");
                    transaccion.setValorDebe(valorHaber.subtract(valorHaber));
                    transaccion.setValorHaber(valorHaber);
                    transaccion.setTipoTransaccion("RET");
                    transaccion.setDetalle("RETIRO");
                    transaccion.setFechaCreacion(new Date());
                    transaccion.setEstado("EXI");
                    transaccion.setFechaUltimoCambio(new Date());
                    transaccion.setVersion(1L);

                    cuentaRepository.save(cuentaPropietario);
                    this.transaccionRepository.save(transaccion);
                    log.info("Retiro realizado con exito. Transaccion creada: {}", transaccion);
                } else {
                    throw new RuntimeException("No posee fondos suficientes");
                }
            } else {
                throw new RuntimeException("No existe el numero de cuenta ingresado");
            }
        } catch (Exception e) {
            throw new CreacionException("Error en creacion de la transaccion:  Error: " + e, e);
        }
    }

    @Transactional
    public void transferir(TransaccionDTO dto) {
        try {
            log.info("Iniciando el proceso de transferencia con la transaccion: {}", dto);
            Transaccion transaccion = TransaccionBuilder.toTransaccion(dto);
            if ("TRE".equals(transaccion.getTipoTransaccion()) || "TEN".equals(transaccion.getTipoTransaccion())) {
                Optional<Cuenta> cuentaOrigen = cuentaRepository.findById(transaccion.getCodCuentaOrigen());
                Optional<Cuenta> cuentaDestino = cuentaRepository.findById(transaccion.getCodCuentaDestino());

                if (cuentaOrigen.isPresent() && cuentaDestino.isPresent()) {
                    Cuenta cuentaO = cuentaOrigen.get();
                    Cuenta cuentaD = cuentaDestino.get();

                    cuentaO.setSaldoContable(
                            cuentaO.getSaldoContable().subtract(transaccion.getValorDebe()));
                    cuentaO.setSaldoDisponible(
                            cuentaO.getSaldoDisponible().subtract(transaccion.getValorDebe()));

                    cuentaD.setSaldoContable(
                            cuentaD.getSaldoContable().add(transaccion.getValorDebe()));
                    cuentaD.setSaldoDisponible(
                            cuentaD.getSaldoDisponible().add(transaccion.getValorDebe()));

                    cuentaRepository.save(cuentaO);
                    cuentaRepository.save(cuentaD);

                    log.info("Transferencia realizada con exito. Cuenta origen: {}, Cuenta destino: {}, Monto: {}",
                            cuentaO.getNumeroCuenta(), cuentaD.getNumeroCuenta(), transaccion.getValorDebe());
                }

                transaccion.setCodUnico(new DigestUtils("MD5").digestAsHex(dto.toString()));
                transaccion.setEstado("EXI");
                transaccion.setFechaCreacion(new Date());
                transaccion.setFechaUltimoCambio(new Date());
                transaccion.setTipoAfectacion("D");
                transaccion.setVersion(1L);
                transaccion.hashCode();
                this.transaccionRepository.save(transaccion);
                log.info("Transaccion creada: {}", transaccion);
            } else {
                throw new RuntimeException("El tipo de cuenta no es compatible con depositos");
            }
        } catch (Exception e) {
            throw new CreacionException(
                    "Error en creacion de la transaccion: " + dto + ", Error: " + e, e);
        }
    }

    public List<TransaccionDTO> buscarPorCodigoCuenta(Integer codCuentaOrigen) {
        log.info("Buscando transacciones por codigo de cuenta de origen: {}", codCuentaOrigen);
        List<TransaccionDTO> dtos = new ArrayList<>();
        for (Transaccion transaccion : this.transaccionRepository.findByCodCuentaOrigen(codCuentaOrigen)) {
            dtos.add(TransaccionBuilder.toDTO(transaccion));
        }
        return dtos;
    }
}
