package com.banquito.core.baking.cuenta.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.baking.cuenta.dto.TasaInteresDTO;
import com.banquito.core.baking.cuenta.service.TasaInteresService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = {"http://localhost:4200", "http://34.86.49.111:4201", "http://34.162.115.216:4202", "http://34.145.219.32:4203", "http://34.145.220.97:4204","http://34.86.49.111:4200","http://34.145.220.97:4204","http://34.145.219.32:4200","http://34.162.115.216:4200"})

@RestController
@RequestMapping("/api/v1/tasaInteres")
public class TasaInteresController {

    private final TasaInteresService tasaInteresService;

    public TasaInteresController(TasaInteresService tasaInteresService) {
        this.tasaInteresService = tasaInteresService;
    }

    @GetMapping
    public ResponseEntity<List<TasaInteresDTO>> Listar() {
        try {
            log.info("Obteniendo la lista de tasas interes");
            return ResponseEntity.ok(this.tasaInteresService.Listar());
        } catch (RuntimeException rte) {
            log.error("Error al obtener la lista de tasa de interes", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<TasaInteresDTO>> ObtenerPorEstado(@RequestParam(name = "estado") String estado) {
        try {
            log.info("Obteniendo tasa interes con el estado: {}", estado);
            return ResponseEntity.ok(this.tasaInteresService.ListarPorEstado(estado));
        } catch (RuntimeException rte) {
            log.error("Error al obtener la tasa de interes", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{codTasaInteres}")
    public ResponseEntity<TasaInteresDTO> ObtenerPorId(@PathVariable(name = "codTasaInteres") Integer codTasaInteres) {
        try {
            log.info("Obteniendo tasa interes con ID: {}", codTasaInteres);
            return ResponseEntity.ok(this.tasaInteresService.BuscarPorId(codTasaInteres));
        } catch (RuntimeException rte) {
            log.error("Error al obtener la tasa de interes", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TasaInteresDTO> Crear(@RequestBody TasaInteresDTO tasaInteresDTO) {
        try {
            log.info("Creando la tasa interes: {}", tasaInteresDTO);
            return ResponseEntity.ok(this.tasaInteresService.Crear(tasaInteresDTO));
        } catch (RuntimeException rte) {
            log.error("Error al crear la tasa de interes", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping
    public ResponseEntity<TasaInteresDTO> CambiarEstado(@RequestParam("codTasaInteres") Integer codTasaInteres, @RequestParam("estado") String estado) {
        try {
            log.info("Actualizando el estado de la tasa interes: {} a {}", codTasaInteres, estado);
            return ResponseEntity.ok(this.tasaInteresService.CambiarEstado(codTasaInteres, estado));
        } catch(RuntimeException rte) {
            log.error("Error al cambiar el estado de la tasa interes", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
