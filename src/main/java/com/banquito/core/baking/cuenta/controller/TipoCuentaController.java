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
import com.banquito.core.baking.cuenta.dto.TipoCuentaDTO;
import com.banquito.core.baking.cuenta.service.TipoCuentaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/tiposcuentas")
public class TipoCuentaController {
    
    private final TipoCuentaService tipoCuentaService;

    public TipoCuentaController(TipoCuentaService tipoCuentaService) {
        this.tipoCuentaService = tipoCuentaService;
    }

    @GetMapping
    public ResponseEntity<List<TipoCuentaDTO>> listarTiposCuentas() {
        log.info("Obteniendo listado de tipos de cuentas");
        return ResponseEntity.ok(this.tipoCuentaService.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCuentaDTO> buscarPorId(@PathVariable(name = "id") String id) {
        log.info("Obteniendo el tipo de cuenta con ID: {}", id);
        try {
            return ResponseEntity.ok(this.tipoCuentaService.obtenerPorId(id));
        } catch(RuntimeException rte) {
            log.error("Error al obtener tipo de cuenta por ID", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody TipoCuentaDTO tipoCuenta) {
        log.info("Se va a crear el tipo de cuenta: {}", tipoCuenta);
        try {
            this.tipoCuentaService.crear(tipoCuenta);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al crear el tipo de cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> actualizar(@RequestBody TipoCuentaDTO tipoCuenta) {
        log.info("Se va a actualizar el tipo de cuenta: {}", tipoCuenta);
        try {
            this.tipoCuentaService.actualizar(tipoCuenta);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al actualizar el tipo de cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name = "id") String id) {
        log.info("Se va a eliminar el tipo de cuenta con ID: {}", id);
        try {
            this.tipoCuentaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al eliminar el tipo de cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
