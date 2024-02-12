package com.banquito.core.baking.cuenta.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banquito.core.baking.cuenta.dto.TarjetaDTO;
import com.banquito.core.baking.cuenta.service.TarjetaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/tarjetas")
public class TarjetaController {
    private TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @GetMapping
    public ResponseEntity<List<TarjetaDTO>> listarTarjetas() {
        log.info("Obteniendo listado de todas las terjetas");
        return ResponseEntity.ok(this.tarjetaService.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarjetaDTO> buscarPorId(@PathVariable(name = "id") Integer id) {
        log.info("Obteniendo tarjeta con ID: {}", id);
        try {
            return ResponseEntity.ok(this.tarjetaService.obtenerPorId(id));
        } catch(RuntimeException rte) {
            log.error("Error al obtener tarjeta por ID", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody TarjetaDTO tarjeta) {
        log.info("Se va a crear la tarjeta: {}", tarjeta);
        try {
            this.tarjetaService.crear(tarjeta);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al crear la tarjeta", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name = "id") Integer id) {
        log.info("Se va a eliminar la tarjeta con ID: {}", id);
        try {
            this.tarjetaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al eliminar la tarjeta", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> actualizar(@RequestBody TarjetaDTO tarjeta) {
        log.info("Se va a actualizar la tarjeta: {}", tarjeta);
        try {
            this.tarjetaService.actualizar(tarjeta);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al actualizar la tarjeta", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/numero/{tarjeta}")
    public ResponseEntity<TarjetaDTO> buscarPorTarjeta(@PathVariable(name = "tarjeta") String numero) {
        log.info("Obteniendo numero de tarjeta: {}", numero);
        try {
            return ResponseEntity.ok(this.tarjetaService.buscarPorTarjeta(numero));
        } catch(RuntimeException rte) {
            log.error("Error al obtener numero de tarjeta", rte);
            return ResponseEntity.notFound().build();
        }
    }
}
