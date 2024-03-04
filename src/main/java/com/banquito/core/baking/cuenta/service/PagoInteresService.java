package com.banquito.core.baking.cuenta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.baking.cuenta.domain.PagoInteres;
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

    public Optional<PagoInteres> obtenerPorId(Integer id) {
        log.info("Buscando pago de interés con ID: {}", id);
        return pagoInteresRepository.findById(id);
    }

    public List<PagoInteres> obtenerPorCuenta(Integer codCuenta) {
        log.info("Buscando pagos de interés para la cuenta: {}", codCuenta);
        return pagoInteresRepository.findByCodCuenta(codCuenta);
    }

    @Transactional
    public void eliminar(Integer id) {
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
