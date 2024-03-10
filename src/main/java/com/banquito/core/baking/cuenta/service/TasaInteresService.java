package com.banquito.core.baking.cuenta.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.baking.cuenta.dao.TasaInteresRepository;
import com.banquito.core.baking.cuenta.domain.TasaInteres;
import com.banquito.core.baking.cuenta.dto.TasaInteresDTO;
import com.banquito.core.baking.cuenta.dto.Builder.TasaInteresBuilder;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TasaInteresService {

    private final TasaInteresRepository tasaInteresRepository;

    public TasaInteresService(TasaInteresRepository tasaInteresRepository) {
        this.tasaInteresRepository = tasaInteresRepository;
    }

    public Optional<TasaInteres> BuscarPorId(Integer id) {
        log.info("Buscando tasa de interés con ID: {}", id);
        return this.tasaInteresRepository.findById(id);
    }

    public Iterable<TasaInteres> Listar() {
        log.info("Listando todas las tasas de interés");
        return this.tasaInteresRepository.findAll();
    }

    @Transactional
    public TasaInteresDTO Crear(TasaInteresDTO dto) {
        log.info("Creando nueva tasa de interes: {}", dto);
        try {
            TasaInteres tasaInteres = TasaInteresBuilder.toTasaInteres(dto);
            LocalDateTime fechaActualTimestamp = LocalDateTime.now();
            
            tasaInteres.setFechaCreacion(Timestamp.valueOf(fechaActualTimestamp));
            tasaInteres.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));

            dto = TasaInteresBuilder.toDTO(this.tasaInteresRepository.save(tasaInteres));
            log.info("La tasa de interes se ha creado exitosamente: {}", dto);
            return dto;
        } catch (Exception e) {
            log.error("Error al crear tasa de interés", e);
            throw new CreacionException("Error al crear tasa de interés: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void Eliminar(Integer id) {
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
