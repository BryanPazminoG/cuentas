package com.banquito.core.baking.cuenta.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.baking.cuenta.dao.TarjetaRepository;
import com.banquito.core.baking.cuenta.domain.Tarjeta;
import com.banquito.core.baking.cuenta.dto.TarjetaDTO;
// import com.banquito.core.baking.cuenta.dto.Builder.TarjetaBuilder;
import com.banquito.core.baking.cuenta.mappers.TarjetaMapper;
import com.banquito.core.baking.cuenta.service.exeption.CreacionException;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TarjetaService {
    private final TarjetaRepository tarjetaRepository;

    public TarjetaService(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    public TarjetaDTO BuscarPorId(Integer codTarjeta) {
        Optional<Tarjeta> tarjeta = this.tarjetaRepository.findById(codTarjeta);
        if (tarjeta.isPresent()) {
            log.info("La tarjeta con ID: {} se ha encontrado EXITOSAMENTE: {}", tarjeta.get());
            //TarjetaDTO dto = TarjetaBuilder.toDTO(tarjeta.get());
            TarjetaDTO dto = TarjetaMapper.mapper.toDTO(tarjeta.get());
            return dto;
        } else {
            log.error("La tarjeta con ID: {} no existe", codTarjeta);
            throw new RuntimeException("No se encontro la tarjeta con ID: " + codTarjeta);
        }
    }

    public TarjetaDTO BuscarPorNumero(String numero) {
        Optional<Tarjeta> tarjeta = this.tarjetaRepository.findByNumero(numero);
        if (tarjeta.isPresent()) {
            log.info("La tarjeta con el numero: {} se ha encontrado EXITOSAMENTE: {}", numero);
            // TarjetaDTO dto = TarjetaBuilder.toDTO(tarjeta.get());
            TarjetaDTO dto = TarjetaMapper.mapper.toDTO(tarjeta.get());
            return dto;
        } else {
            log.error("La tarjeta con el numero: {} no existe", numero);
            throw new RuntimeException("No se encontro la tarjeta con el numero: " + numero);
        }
    }

    public List<TarjetaDTO> BuscarPorCuenta(Integer codCuenta) {
        List<TarjetaDTO> listDTO = new ArrayList<>();
        List<Tarjeta> listTarjeta = this.tarjetaRepository.findByCodCuentaOrderByFechaEmision(codCuenta);
        for (Tarjeta tarjeta : listTarjeta) {
            // listDTO.add(TarjetaBuilder.toDTO(tarjeta));
            listDTO.add(TarjetaMapper.mapper.toDTO(tarjeta));
        }
        log.info("Se encontro el listando de las tarjetas de la cuenta {} : {}", codCuenta, listDTO);
        return listDTO;
    }

    @Transactional
    public TarjetaDTO Crear(TarjetaDTO dto) {
        try {

            // Tarjeta tarjeta = TarjetaBuilder.toTarjeta(dto);
            Tarjeta tarjeta = TarjetaMapper.mapper.toEntity(dto);
            LocalDateTime fechaActualTimestamp = LocalDateTime.now();

            tarjeta.setFechaEmision(Timestamp.valueOf(fechaActualTimestamp));
            tarjeta.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
            tarjeta.setEstado("ACT");
            // dto =  TarjetaBuilder.toDTO(this.tarjetaRepository.save(tarjeta));
            dto =  TarjetaMapper.mapper.toDTO(this.tarjetaRepository.save(tarjeta));

            log.info("La tarjeta se ha creado exitosamente: {}", dto);
            return dto;

        } catch (Exception e) {
            log.error("Error al crear la  tarjeta: {}", dto);
            throw new CreacionException("Error en creacion de la tarjeta: " + dto + ", Error: " + e, e);
        }
    }

    @Transactional
    public TarjetaDTO CambiarEstado(Integer codTarjeta, String estado) {
        try {
            if("ACT".equals(estado) || "INA".equals(estado) || "BLO".equals(estado)|| "VEN".equals(estado)){
                Optional<Tarjeta> tarjeta = this.tarjetaRepository.findById(codTarjeta);
                if(tarjeta.isPresent()){
                    log.info("Se obtuvo la tarjeta con el ID {}", codTarjeta);
                    tarjeta.get().setEstado(estado);
                    LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                    tarjeta.get().setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                    // TarjetaDTO dto = TarjetaBuilder.toDTO(this.tarjetaRepository.save(tarjeta.get()));
                    TarjetaDTO dto = TarjetaMapper.mapper.toDTO(this.tarjetaRepository.save(tarjeta.get()));
                    log.info("El estado de la tarjeta se ha actualizado correctamente a {}", estado);
                    return dto;
                }else{
                    log.error("La tarjeta con ID {} no existe", codTarjeta);
                    throw new RuntimeException("La tarjeta con ID " + codTarjeta + " no existe");
                }
            }else{
                log.error("El estado {} es invalido", estado);
                throw new RuntimeException("Estado ingresado invalido: " + estado);
            }
        } catch (Exception e) {
            throw new CreacionException("Ocurrio un error al actualizar la tarjeta, error: " + e.getMessage(), e);
        }
    }
}
