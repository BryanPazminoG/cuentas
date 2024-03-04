package com.banquito.core.baking.cuenta.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.baking.cuenta.dao.TasaInteresRepository;
import com.banquito.core.baking.cuenta.domain.TasaInteres;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TasaInteresService {

    private final TasaInteresRepository tasaInteresRepository;

    public TasaInteresService(TasaInteresRepository tasaInteresRepository) {
        this.tasaInteresRepository = tasaInteresRepository;
    }

    public Optional<TasaInteres> obtenerPorId(Integer id) {
        log.info("Buscando tasa de interés con ID: {}", id);
        return this.tasaInteresRepository.findById(id);
    }

    public Iterable<TasaInteres> listarTodo() {
        log.info("Listando todas las tasas de interés");
        return this.tasaInteresRepository.findAll();
    }

    @Transactional
    public TasaInteres crear(TasaInteres tasaInteres) {
        log.info("Creando nueva tasa de interés: {}", tasaInteres);
        try {
            tasaInteres.setFechaCreacion(new Date(0));
            return this.tasaInteresRepository.save(tasaInteres);
        } catch (Exception e) {
            log.error("Error al crear tasa de interés", e);
            throw new CreacionException("Error al crear tasa de interés: " + e.getMessage(), e);
        }
    }

    @Transactional
    public List<TasaInteres> obtenerPorTipoCuenta(Integer tipoCuenta) {
        log.info("Buscando tasas de interés por tipo de cuenta: {}", tipoCuenta);
        return this.tasaInteresRepository.findByCodTipoCuenta(tipoCuenta);
    }

    @Transactional
    public void eliminar(Integer id) {
        log.info("Eliminando tasa de interés con ID: {}", id);
        try {
            if (this.tasaInteresRepository.existsById(id)) {
                this.tasaInteresRepository.deleteById(id);
                log.info("Tasa de interés eliminada correctamente");
            } else {
                log.error("La tasa de interés con ID: {} no existe", id);
                throw new CreacionException("Tasa de interés no encontrada para eliminar");
            }
        } catch (Exception e) {
            log.error("Error al eliminar tasa de interés", e);
            throw new CreacionException("Error al eliminar tasa de interés: " + e.getMessage(), e);
        }
    }
}
