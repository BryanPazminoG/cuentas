package com.banquito.core.baking.cuenta.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = {"http://localhost:4200", "http://34.173.161.134:4201", "http://34.176.205.203:4202", 
                        "http://34.176.102.118:4203", "http://34.176.137.180:4204"})
//@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/api/v1/tiposCuentas")
public class TipoCuentaController {
    
    private final TipoCuentaService tipoCuentaService;

    public TipoCuentaController(TipoCuentaService tipoCuentaService) {
        this.tipoCuentaService = tipoCuentaService;
    }

    @GetMapping
    public ResponseEntity<List<TipoCuentaDTO>> Listar() {
        try {
            log.info("Obteniendo listado de tipos de cuentas");
            return ResponseEntity.ok(this.tipoCuentaService.Listar());
        } catch(RuntimeException rte) {
            log.error("Error al listar los tipo de cuentas", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estados/{estado}")
    public ResponseEntity<List<TipoCuentaDTO>> ObtenerPorEstado(@PathVariable(name = "estado") String estado) {
        try {
            log.info("Obteniendo tipo cuenta por el estado: {}", estado);
            return ResponseEntity.ok(this.tipoCuentaService.ListarPorEstado(estado));
        } catch (RuntimeException rte) {
            log.error("Error al obtener el tipo cuenta", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{codTipoCuenta}")
    public ResponseEntity<TipoCuentaDTO> ObtenerPorId(@PathVariable(name = "codTipoCuenta") String codTipoCuenta) {
        try {
            log.info("Obteniendo el tipo de cuenta con ID: {}", codTipoCuenta);
            return ResponseEntity.ok(this.tipoCuentaService.BuscarPorId(codTipoCuenta));
        } catch(RuntimeException rte) {
            log.error("Error al obtener el tipo de cuenta", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoCuentaDTO> Crear(@RequestBody TipoCuentaDTO tipoCuenta) {
        try {
            log.info("Creando el tipo de cuenta: {}", tipoCuenta);
            return ResponseEntity.ok(this.tipoCuentaService.Crear(tipoCuenta));
        } catch(RuntimeException rte) {
            log.error("Error al crear el tipo de cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<TipoCuentaDTO> Actualizar(@RequestBody TipoCuentaDTO tipoCuenta) {
        try {
            log.info("Actualizando el tipo de cuenta: {}", tipoCuenta);
            return ResponseEntity.ok(this.tipoCuentaService.Actualizar(tipoCuenta));
        } catch(RuntimeException rte) {
            log.error("Error al actualizar el tipo de cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
