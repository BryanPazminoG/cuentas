package com.banquito.core.baking.cuenta.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.baking.cuenta.dto.PagoInteresDTO;
import com.banquito.core.baking.cuenta.service.PagoInteresService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @CrossOrigin(origins = {"http://localhost:4200", "http://34.173.161.134:4201", "http://34.176.205.203:4202",
//                         "http://34.176.102.118:4203", "http://34.176.137.180:4204"})

@RestController
@RequestMapping("/api/v1/pagos-interes")
public class PagoInteresController {

    private final PagoInteresService pagoInteresService;

    public PagoInteresController(PagoInteresService pagoInteresService) {
        this.pagoInteresService = pagoInteresService;
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<PagoInteresDTO>> buscarPorCuenta(@PathVariable(name = "cuentaId") Integer cuentaId) {
        log.info("Obteniendo pagos de interés por cuenta: {}", cuentaId);
        try {
            return ResponseEntity.ok(this.pagoInteresService.BuscarPorCuenta(cuentaId));
        } catch(RuntimeException rte) {
            log.error("Error al obtener pagos de interés por cuenta", rte);
            return ResponseEntity.notFound().build();
        }
    }

    // @PostMapping
    // public ResponseEntity<PagoInteresDTO> crear(@RequestBody PagoInteresDTO pagoInteres) {
    //     log.info("Se va a crear el pago de interés: {}", pagoInteres);
    //     try {
    //         return ResponseEntity.ok(this.pagoInteresService.Crear(pagoInteres));
    //     } catch(RuntimeException rte) {
    //         log.error("Error al crear el pago de interés", rte);
    //         return ResponseEntity.badRequest().build();
    //     }
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name = "id") Integer id) {
        log.info("Se va a eliminar el pago de interés con ID: {}", id);
        try {
            this.pagoInteresService.Eliminar(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al eliminar el pago de interés", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
