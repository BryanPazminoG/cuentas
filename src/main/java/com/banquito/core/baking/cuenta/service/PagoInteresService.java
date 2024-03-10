package com.banquito.core.baking.cuenta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
// import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.banquito.core.baking.cuenta.domain.PagoInteres;
import com.banquito.core.baking.cuenta.dto.PagoInteresDTO;
import com.banquito.core.baking.cuenta.dto.Builder.PagoInteresBuilder;
import com.banquito.core.baking.cuenta.dao.PagoInteresRepository;
import lombok.extern.slf4j.Slf4j;

@EnableAsync
@Service
@Slf4j
public class PagoInteresService {

    private final PagoInteresRepository pagoInteresRepository;

    public PagoInteresService(PagoInteresRepository pagoInteresRepository) {
        this.pagoInteresRepository = pagoInteresRepository;
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

    // @Async
    // @Scheduled(cron = "0 03 16 * * MON-SUN")
    // public void GenerarInteres() {

    // for (CreditoDTO creditoDTO : this.ListarCreditosEstado("VIG")) {
    // BigDecimal montoCredito = creditoDTO.getCapitalPendiente();
    // BigDecimal tasaInteresVigente = creditoDTO.getTasaInteres();
    // BigDecimal interesGenerado =
    // montoCredito.multiply(tasaInteresVigente.divide(new
    // BigDecimal("36500"),MathContext.DECIMAL128));
    // LocalDate fechaActualDate = LocalDate.now();
    // TablaAmortizacion cuotaActiva =
    // this.tablaAmortizacionRepository.findByPKCodCreditoAndEstadoOrderByFechaPlanificadaPago(creditoDTO.getCodCredito(),
    // "PRX").get(0);

    // InteresAcumulado interesAcumulado = new InteresAcumulado();
    // interesAcumulado.setCodCredito(creditoDTO.getCodCredito());
    // interesAcumulado.setNumeroCuota(cuotaActiva.getPK().getCodCuota());
    // interesAcumulado.setCapitalPendiente(montoCredito);
    // interesAcumulado.setTasaInteresVigente(tasaInteresVigente);
    // interesAcumulado.setInteresGenerado(interesGenerado);
    // interesAcumulado.setFechaCreacion(Date.valueOf(fechaActualDate));
    // interesAcumulado.setEstado("PEN");

    // this.interesAcumuladoRepository.save(interesAcumulado);

    // log.info("Creado el interes acumulado : {} ", interesAcumulado);
    // }
    // }
}
