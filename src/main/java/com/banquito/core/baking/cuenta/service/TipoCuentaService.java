package com.banquito.core.baking.cuenta.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.baking.cuenta.dao.TipoCuentaRepository;
import com.banquito.core.baking.cuenta.domain.TipoCuenta;
import com.banquito.core.baking.cuenta.dto.TipoCuentaDTO;
// import com.banquito.core.baking.cuenta.dto.Builder.TipoCuentaBuilder;
import com.banquito.core.baking.cuenta.mappers.TipoCuentaMapper;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TipoCuentaService {
    private final TipoCuentaRepository tipoCuentaRepository;

    public TipoCuentaService(TipoCuentaRepository tipoCuentaRepository) {
        this.tipoCuentaRepository = tipoCuentaRepository;
    }

    public List<TipoCuentaDTO> Listar() {
        List<TipoCuentaDTO> listDTO = new ArrayList<>();
        for (TipoCuenta tipoCuenta : this.tipoCuentaRepository.findAll()) {
            // listDTO.add(TipoCuentaBuilder.toDTO(tipoCuenta));
            listDTO.add(TipoCuentaMapper.mapper.toDTO(tipoCuenta));
        }
        log.info("Se encontro el listando de los siguientes tipo cuenta: {}", listDTO);

        return listDTO;
    }

    public List<TipoCuentaDTO> ListarPorEstado(String estado) {
        if ("ACT".equals(estado) || "INA".equals(estado)) {
            List<TipoCuentaDTO> listDTO = new ArrayList<>();
            for (TipoCuenta tipoCuenta : this.tipoCuentaRepository.findByEstadoOrderByFechaCreacion(estado)) {
                // listDTO.add(TipoCuentaBuilder.toDTO(tipoCuenta));
                listDTO.add(TipoCuentaMapper.mapper.toDTO(tipoCuenta));
            }
            log.info("Se obtuvo el tipo cuenta con el estado: ", estado);
            return listDTO;
        } else {
            log.error("El estado {} es invalido", estado);
            throw new RuntimeException("Estado ingresado invalido: " + estado);
        }
    }

    public TipoCuentaDTO BuscarPorId(String codTipoCuenta) {
        Optional<TipoCuenta> tipoCuenta = this.tipoCuentaRepository.findById(codTipoCuenta);
        if (tipoCuenta.isPresent()) {
            log.info("El tipoCuenta con ID: {} se ha encontrado EXITOSAMENTE: {}", tipoCuenta.get());
            // TipoCuentaDTO dto = TipoCuentaBuilder.toDTO(tipoCuenta.get());
            TipoCuentaDTO dto = TipoCuentaMapper.mapper.toDTO(tipoCuenta.get());
            return dto;
        } else {
            log.error("El tipo cuenta con ID: {} no existe", codTipoCuenta);
            throw new RuntimeException("No se encontro el tipo de cuenta con ID: " + codTipoCuenta);
        }
    }

    @Transactional
    public TipoCuentaDTO Crear(TipoCuentaDTO dto) {
        try {
            // TipoCuenta tipoCuenta = TipoCuentaBuilder.toTipoCuenta(dto);
            TipoCuenta tipoCuenta = TipoCuentaMapper.mapper.toEntity(dto);
            LocalDateTime fechaActualTimestamp = LocalDateTime.now();
            tipoCuenta.setFechaCreacion(Timestamp.valueOf(fechaActualTimestamp));
            tipoCuenta.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
            // dto = TipoCuentaBuilder.toDTO(this.tipoCuentaRepository.save(tipoCuenta));
            dto = TipoCuentaMapper.mapper.toDTO(this.tipoCuentaRepository.save(tipoCuenta));
            log.info("Se creo el tipo de cuenta: {}", tipoCuenta);
            return dto;
        } catch (Exception e) {
            log.error("Error al crear el tipo cuenta", dto);
            throw new RuntimeException("Error al crear el tipo de cuenta.", e);
        }
    }

    @Transactional
    public TipoCuentaDTO Actualizar(TipoCuentaDTO dto) {
        try {

            Optional<TipoCuenta> tipoCuenta = tipoCuentaRepository.findById(dto.getCodTipoCuenta());
            if (tipoCuenta.isPresent()) {
                // tipoCuenta = Optional.ofNullable(TipoCuentaBuilder.toTipoCuenta(dto));
                tipoCuenta = Optional.ofNullable(TipoCuentaMapper.mapper.toEntity(dto));
                LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                tipoCuenta.get().setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                // dto = TipoCuentaBuilder.toDTO(this.tipoCuentaRepository.save(tipoCuenta.get()));
                dto = TipoCuentaMapper.mapper.toDTO(this.tipoCuentaRepository.save(tipoCuenta.get()));
                log.info("Se actualizaron los datos del tipo de cuenta: {} Exitosamente", tipoCuenta);
                return dto;
            } else {
                log.error("El tipo cuenta con ID: {} no existe", dto.getCodTipoCuenta());
                throw new RuntimeException("No se encontro el tipo credito con el ID: " + dto.getCodTipoCuenta());
            }
        } catch (Exception e) {
            log.error("Error al actualizar el tipo cuenta", dto);
            throw new RuntimeException("Error al actualizar tipo de cuenta.", e);
        }
    }
}