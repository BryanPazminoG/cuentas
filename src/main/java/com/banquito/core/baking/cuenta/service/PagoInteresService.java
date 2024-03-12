package com.banquito.core.baking.cuenta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.domain.PagoInteres;
import com.banquito.core.baking.cuenta.domain.TasaInteres;
import com.banquito.core.baking.cuenta.domain.TipoCuenta;
import com.banquito.core.baking.cuenta.domain.Transaccion;
import com.banquito.core.baking.cuenta.dao.TipoCuentaRepository;
import com.banquito.core.baking.cuenta.dao.TransaccionRepository;
import com.banquito.core.baking.cuenta.dto.PagoInteresDTO;
import com.banquito.core.baking.cuenta.dto.Builder.PagoInteresBuilder;
import com.banquito.core.baking.cuenta.dao.CuentaRepository;
import com.banquito.core.baking.cuenta.dao.PagoInteresRepository;
import com.banquito.core.baking.cuenta.dao.TasaInteresRepository;

import java.math.MathContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;

@EnableAsync
@Service
@Slf4j
public class PagoInteresService {

    private final PagoInteresRepository pagoInteresRepository;
    private final CuentaRepository cuentaRepository;
    private final TipoCuentaRepository tipoCuentaRepository;
    private final TasaInteresRepository tasaInteresRepository;
    private final TransaccionRepository transaccionRepository;

    public PagoInteresService(PagoInteresRepository pagoInteresRepository, CuentaRepository cuentaRepository,
            TipoCuentaRepository tipoCuentaRepository, TasaInteresRepository tasaInteresRepository,
            TransaccionRepository transaccionRepository) {
        this.pagoInteresRepository = pagoInteresRepository;
        this.cuentaRepository = cuentaRepository;
        this.tipoCuentaRepository = tipoCuentaRepository;
        this.tasaInteresRepository = tasaInteresRepository;
        this.transaccionRepository = transaccionRepository;
    }

    public PagoInteresDTO BuscarPorId(Integer codPagoInteres) {

        Optional<PagoInteres> pagoInteres = this.pagoInteresRepository.findById(codPagoInteres);
        if (pagoInteres.isPresent()) {
            log.info("El pago interes con ID: {} se ha encontrado EXITOSAMENTE: {}", pagoInteres.get());
            PagoInteresDTO dto = PagoInteresBuilder.toDTO(pagoInteres.get());
            return dto;
        } else {
            log.error("El pago interes con ID: {} no existe", codPagoInteres);
            throw new RuntimeException("No se encontro el pago interes con ID: " + codPagoInteres);
        }

    }

    public List<PagoInteresDTO> BuscarPorCuenta(Integer codCuenta) {
        List<PagoInteresDTO> listDTO = new ArrayList<>();
        List<PagoInteres> listPagoInteres = this.pagoInteresRepository.findByCodCuentaOrderByFechaEjecucion(codCuenta);
        for (PagoInteres pagoInteres : listPagoInteres) {
            listDTO.add(PagoInteresBuilder.toDTO(pagoInteres));
        }
        log.info("Se encontro el listando de los pagos interes de la cuenta {} : {}", codCuenta, listDTO);
        return listDTO;
    }

    @Async
    @Scheduled(cron = "0 0 0 * * MON-SUN")
    public void GenerarInteres() {
        for (Cuenta cuenta : this.cuentaRepository.findByEstadoOrderByFechaCreacion("ACT")) {

            TipoCuenta tipoCuenta = this.tipoCuentaRepository.findById(cuenta.getCodTipoCuenta()).get();
            TasaInteres tasaInteres = this.tasaInteresRepository.findById(tipoCuenta.getCodTasaInteres()).get();
            BigDecimal capital = cuenta.getSaldoDisponible();
            BigDecimal tasaInteresVigente = tasaInteres.getTasaInteres();
            BigDecimal interesGanado = capital
                    .multiply(tasaInteresVigente.divide(new BigDecimal("36500"), MathContext.DECIMAL128));

            LocalDateTime fechaActualTimestamp = LocalDateTime.now();

            PagoInteres pagoInteres = new PagoInteres();
            pagoInteres.setCodCuenta(cuenta.getCodCuenta());
            pagoInteres.setCapital(capital);
            pagoInteres.setInteresVigente(tasaInteresVigente);
            pagoInteres.setInteresGanado(interesGanado);
            pagoInteres.setFechaEjecucion(Timestamp.valueOf(fechaActualTimestamp));

            pagoInteres = this.pagoInteresRepository.save(pagoInteres);

            log.info("interes ganado creado Exitosamente: {} ", pagoInteres.getCodPagoInteres());

            log.info("Iniciando el proceso de deposito en la cuenta: {}", cuenta.getCodCuenta());

            Transaccion transaccion = new Transaccion();
            transaccion.setCodCuenta(cuenta.getCodCuenta());
            transaccion.setTipoAfectacion("D");
            transaccion.setValorDebe(interesGanado);
            transaccion.setValorHaber(interesGanado);
            transaccion.setTipoTransaccion("DEP");
            transaccion.setDetalle("Interes Ganado " + Timestamp.valueOf(fechaActualTimestamp));
            transaccion.setFechaCreacion(Timestamp.valueOf(fechaActualTimestamp));
            transaccion.setEstado("EXI");
            transaccion.setFechaAfectacion(Timestamp.valueOf(fechaActualTimestamp));
            transaccion.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));

            transaccion.setCodUnico(new DigestUtils("MD2").digestAsHex(transaccion.toString()));

            transaccion = transaccionRepository.save(transaccion);

            log.info("Deposito realizado con exito: {}", transaccion.getCodTransaccion());

            log.info("Actualizando saldo de la cuenta: {}", cuenta.getCodCuenta());
            cuenta.setSaldoContable(cuenta.getSaldoContable().add(interesGanado));
            cuenta.setSaldoDisponible(cuenta.getSaldoDisponible().add(interesGanado));
            cuenta.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));

            cuenta = this.cuentaRepository.save(cuenta);
            
            log.info("Actualizacion de saldo realizado con exito: {}", cuenta.getCodCuenta());
        }
    }
}
