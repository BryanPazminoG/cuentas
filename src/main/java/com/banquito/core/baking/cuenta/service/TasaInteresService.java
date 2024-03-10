package com.banquito.core.baking.cuenta.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.banquito.core.baking.cuenta.dao.TasaInteresRepository;
import com.banquito.core.baking.cuenta.domain.TasaInteres;
import com.banquito.core.baking.cuenta.dto.TasaInteresDTO;
// import com.banquito.core.baking.cuenta.dto.Builder.TasaInteresBuilder;
import com.banquito.core.baking.cuenta.mappers.TasaInteresMapper;
import com.banquito.core.baking.cuenta.service.exeption.CreacionException;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TasaInteresService {

    private final TasaInteresRepository tasaInteresRepository;

    public TasaInteresService(TasaInteresRepository tasaInteresRepository) {
        this.tasaInteresRepository = tasaInteresRepository;
    }

    public List<TasaInteresDTO> Listar() {
        List<TasaInteresDTO> listDTO = new ArrayList<>();
        
        for (TasaInteres tasaInteres : this.tasaInteresRepository.findAll()){
            // listDTO.add(TasaInteresBuilder.toDTO(tasaInteres));
            listDTO.add(TasaInteresMapper.mapper.toDTO(tasaInteres));
        }
        log.info("Se encontro el listando de las siguientes tasas de interes: {}", listDTO);
        return listDTO;
    }

    public List<TasaInteresDTO> ListarPorEstado(String estado) {
        if ("ACT".equals(estado) || "INA".equals(estado)) {
            List<TasaInteresDTO> listDTO = new ArrayList<>();
            for(TasaInteres tasaInteres : this.tasaInteresRepository.findByEstadoOrderByFechaCreacion(estado)){
                // listDTO.add(TasaInteresBuilder.toDTO(tasaInteres));
                listDTO.add(TasaInteresMapper.mapper.toDTO(tasaInteres));
            }
            log.info("Se obtuvo la lista de tasa de interes con el estado: ", estado);
            return listDTO;
        }else{
            log.error("El estado {} es invalido", estado);
            throw new RuntimeException("Estado ingresado invalido: " + estado);
        }
    }

    public TasaInteresDTO BuscarPorId(Integer codTasaInteres) {
        Optional<TasaInteres> tasaInteres = this.tasaInteresRepository.findById(codTasaInteres);
        if(tasaInteres.isPresent()){
            log.info("La tasa de interés con ID: {} se ha encontrado EXITOSAMENTE", codTasaInteres);
            // TasaInteresDTO dto = TasaInteresBuilder.toDTO(tasaInteres.get());
            TasaInteresDTO dto = TasaInteresMapper.mapper.toDTO(tasaInteres.get());
            return dto;
        }else{
            log.error("La tasa de interés con ID: {} no existe", codTasaInteres);
            throw new RuntimeException("No se han encontrado la tasa de interés con el ID" + codTasaInteres);
        }
    }

    @Transactional
    public TasaInteresDTO Crear(TasaInteresDTO dto) {
        try {
            // TasaInteres tasaInteres = TasaInteresBuilder.toTasaInteres(dto);
            TasaInteres tasaInteres = TasaInteresMapper.mapper.toEntity(dto);
            LocalDateTime fechaActualTimestamp = LocalDateTime.now();
            
            tasaInteres.setFechaCreacion(Timestamp.valueOf(fechaActualTimestamp));
            tasaInteres.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));

            // dto = TasaInteresBuilder.toDTO(this.tasaInteresRepository.save(tasaInteres));
            dto = TasaInteresMapper.mapper.toDTO(this.tasaInteresRepository.save(tasaInteres));
            log.info("La tasa de interes se ha creado exitosamente: {}", dto);
            return dto;
        } catch (Exception e) {
            log.error("Error al crear tasa de interés", e);
            throw new CreacionException("Error al crear tasa de interés: " + e.getMessage(), e);
        }
    }

    @Transactional
    public TasaInteresDTO CambiarEstado(Integer codTasaInteres, String estado) {
        try {
            if("ACT".equals(estado) || "INA".equals(estado)){
                Optional<TasaInteres> tasaInteres = this.tasaInteresRepository.findById(codTasaInteres);
                if(tasaInteres.isPresent()){
                    log.info("Se obtuvo la tasa de interes con el ID {}", codTasaInteres);
                    tasaInteres.get().setEstado(estado);
                    LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                    tasaInteres.get().setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                    // TasaInteresDTO dto = TasaInteresBuilder.toDTO(this.tasaInteresRepository.save(tasaInteres.get()));
                    TasaInteresDTO dto = TasaInteresMapper.mapper.toDTO(this.tasaInteresRepository.save(tasaInteres.get()));
                    log.info("El estado de la tasa interes se ha actalizado correctamente a {}", estado);
                    return dto;
                }else{
                    log.error("La tasa de interes con ID {} no existe", codTasaInteres);
                    throw new RuntimeException("La tasa de interes con ID " + codTasaInteres + " no existe");
                }
            }else{
                log.error("El estado {} es invalido", estado);
                throw new RuntimeException("Estado ingresado invalido: " + estado);
            }
        } catch (Exception e) {
            throw new CreacionException("Ocurrio un error al actualizar la tasaInteres, error: " + e.getMessage(), e);
        }
    }
}
