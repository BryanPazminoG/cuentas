package com.banquito.core.baking.cuenta.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.baking.cuenta.dto.TarjetaDTO;
import com.banquito.core.baking.cuenta.service.TarjetaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @CrossOrigin(origins = {"http://localhost:4200", "http://34.173.161.134:4201", "http://34.176.205.203:4202", 
//                         "http://34.176.102.118:4203", "http://34.176.137.180:4204"})
//@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/api/v1/tarjetas")
public class TarjetaController {
    private TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @GetMapping("/{codTarjeta}")
    public ResponseEntity<TarjetaDTO> ObtenerPorId(@PathVariable(name = "codTarjeta") Integer codTarjeta) {
        try {
            log.info("Obteniendo tarjeta con ID: {}", codTarjeta);
            return ResponseEntity.ok(this.tarjetaService.BuscarPorId(codTarjeta));
        } catch(RuntimeException rte) {
            log.error("Error al obtener tarjeta", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<TarjetaDTO> ObtenerPorNumero(@PathVariable(name = "numero") String numero) {
        try {
            log.info("Obteniendo tarjeta por el numero: {}", numero);
            return ResponseEntity.ok(this.tarjetaService.BuscarPorNumero(numero));
        } catch(RuntimeException rte) {
            log.error("Error al obtener tarjeta", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cuenta/{codCuenta}")
    public ResponseEntity<List<TarjetaDTO>> ObtenerPorCuenta(@PathVariable(name = "codCuenta") Integer codCuenta) {
        try {
            log.info("Obteniendo tarjetas por la cuenta: {}", codCuenta);
            return ResponseEntity.ok(this.tarjetaService.BuscarPorCuenta(codCuenta));
        } catch(RuntimeException rte) {
            log.error("Error al obtener tarjeta", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TarjetaDTO> Crear(@RequestBody TarjetaDTO tarjeta) {
        try {
            log.info("Creando la tarjeta: {}", tarjeta);
            return ResponseEntity.ok(this.tarjetaService.Crear(tarjeta));
        } catch(RuntimeException rte) {
            log.error("Error al crear la tarjeta", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping
    public ResponseEntity<TarjetaDTO> CambiarEstado(@RequestParam("codTarjeta") Integer codTarjeta, @RequestParam("estado") String estado) {
        try {
            log.info("Actualizando el estado de la tarjeta: {} a {}", codTarjeta, estado);
            return ResponseEntity.ok(this.tarjetaService.CambiarEstado(codTarjeta, estado));
        } catch(RuntimeException rte) {
            log.error("Error al cambiar el estado de la tarjeta", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
