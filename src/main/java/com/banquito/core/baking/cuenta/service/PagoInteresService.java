package com.banquito.core.baking.cuenta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.baking.cuenta.domain.PagoInteres;
import com.banquito.core.baking.cuenta.dto.PagoInteresDTO;
import com.banquito.core.baking.cuenta.dto.Builder.PagoInteresBuilder;
import com.banquito.core.baking.cuenta.dao.PagoInteresRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PagoInteresService {

    private final PagoInteresRepository pagoInteresRepository;

    public PagoInteresService(PagoInteresRepository pagoInteresRepository) {
        this.pagoInteresRepository = pagoInteresRepository;
    }

    public Optional<PagoInteres> BuscarPorId(Integer id) {
        log.info("Buscando pago de interés con ID: {}", id);
        return pagoInteresRepository.findById(id);
    }

    public List<PagoInteresDTO> BuscarPorCuenta(Integer codCuenta) {
        List<PagoInteresDTO> listDTO = new ArrayList<>();
        log.info("Buscando pagos de interes para la cuenta: {}", codCuenta);
        List<PagoInteres> listPagoInteres = this.pagoInteresRepository.findByCodCuentaOrderByFechaEjecucion(codCuenta);
        for (PagoInteres pagoInteres : listPagoInteres) {
            listDTO.add(PagoInteresBuilder.toDTO(pagoInteres));
        }
        log.info("Los pagos de Interes encontrados son: {}", listDTO);
        return listDTO;
    }

    @Transactional
    public void Eliminar(Integer id) {
        log.info("Eliminando pago de interés con ID: {}", id);
        if (pagoInteresRepository.existsById(id)) {
            pagoInteresRepository.deleteById(id);
            log.info("Pago de interés eliminado correctamente");
        } else {
            log.error("El pago de interés con ID: {} no existe", id);
            throw new RuntimeException("Pago de interés no encontrado para eliminar");
        }
    }
}
