package com.banquito.core.baking.cuenta.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.baking.cuenta.dao.TipoCuentaRepository;
import com.banquito.core.baking.cuenta.domain.TipoCuenta;
import com.banquito.core.baking.cuenta.dto.TipoCuentaBuilder;
import com.banquito.core.baking.cuenta.dto.TipoCuentaDTO;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TipoCuentaService {
    private final TipoCuentaRepository tipoCuentaRepository;

    public TipoCuentaService(TipoCuentaRepository tipoCuentaRepository) {
        this.tipoCuentaRepository = tipoCuentaRepository;
    }

    public TipoCuentaDTO obtenerPorId(String codTipoCuenta) {
        log.info("Obteniendo tipo de cuenta con ID: {}", codTipoCuenta);
        Optional<TipoCuenta> optTipoCuenta = this.tipoCuentaRepository.findById(codTipoCuenta);
        if (optTipoCuenta.isPresent()) {
            log.info("Tipo de cuenta obtenido: {}", optTipoCuenta.get());
            return TipoCuentaBuilder.toDTO(optTipoCuenta.get());
        } else {
            throw new RuntimeException("No se encontro el tipo de cuenta con ID: " + codTipoCuenta);
        }
    }
    
    public List<TipoCuentaDTO> listarTodo() {
        log.info("Se va a obtener todos los tipos de cuentas");
        List<TipoCuentaDTO> dtos = new ArrayList<>();
        for (TipoCuenta tipoCuenta : this.tipoCuentaRepository.findAll()) {
            dtos.add(TipoCuentaBuilder.toDTO(tipoCuenta));
        }
        return dtos;
    }

    @Transactional
    public void crear(TipoCuentaDTO dto) {
        try {
            TipoCuenta tipoCuenta = TipoCuentaBuilder.toTipoCuenta(dto);
            tipoCuenta.setFechaCreacion(new Date());
            this.tipoCuentaRepository.save(tipoCuenta);
            log.info("Se creo el tipo de cuenta: {}", tipoCuenta);
        } catch (Exception e) {

            throw new CreacionException("Error al crear tipo de cuenta: ", e);
        }
    }

    @Transactional
    public void actualizar(TipoCuentaDTO dto) {
        try {
            TipoCuenta tipoAux = this.tipoCuentaRepository.findById(dto.getCodTipoCuenta()).get();
            TipoCuenta tipoCuentaTmp = TipoCuentaBuilder.toTipoCuenta(dto);
            TipoCuenta tipoCuenta = TipoCuentaBuilder.copyTipoCuenta(tipoCuentaTmp, tipoAux);
            tipoCuenta.setFechaUltimoCambio(new Date());
            this.tipoCuentaRepository.save(tipoCuenta);
            log.info("Se actualizaron los datos del tipo de cuenta: {}", tipoCuenta);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar tipo de cuenta.", e);
        }
    }

    @Transactional
    public void eliminar(String id) {
        try {
            Optional<TipoCuenta> tipoCuenta = this.tipoCuentaRepository.findById(id);
            if (tipoCuenta.isPresent()) {
                this.tipoCuentaRepository.delete(tipoCuenta.get());
                log.info("Se elimino con exito el tipo de cuenta: {}", tipoCuenta);
            } else {
                throw new RuntimeException("El tipo de cuenta con ID: " + id + " no existe");
            }
        } catch (Exception e) {
            throw new CreacionException("Ocurrio un error al eliminar el tipo de cuenta, error: " + e.getMessage(), e);
        }
    }
}