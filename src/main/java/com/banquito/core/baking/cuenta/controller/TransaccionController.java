package com.banquito.core.baking.cuenta.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.baking.cuenta.dto.TransaccionDTO;
import com.banquito.core.baking.cuenta.service.TransaccionService;
import com.banquito.core.baking.cuenta.service.exeption.CreacionException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = {"http://localhost:4200", "http://34.86.49.111:4201", "http://34.162.115.216:4202", "http://34.145.219.32:4203", "http://34.145.220.97:4204"})
// @CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET,
// RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/api/v1/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionDTO> obtenerPorId(@PathVariable("id") Integer id) {
        log.info("Recibida solicitud para obtener la transacción con ID: {}", id);
        try {
            return ResponseEntity.ok(this.transaccionService.buscarPorId(id));
        } catch (RuntimeException rte) {
            log.error("Error al obtener la transacción: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody TransaccionDTO transaccion) {
        log.info("Se va a guardar la transaccion: {}", transaccion);
        try {
            this.transaccionService.crear(transaccion);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte) {
            log.error("Error al generar la transaccion: {}", transaccion);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cuentas/{codCuenta}")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransacionesCliente(
            @PathVariable("codCuenta") Integer codCuenta) {
        log.info("Recibida solicitud para obtener transacciones para la cuenta: {}", codCuenta);
        try {
            List<TransaccionDTO> transacciones = this.transaccionService.buscarPorCodigoCuenta(codCuenta);
            return ResponseEntity.ok(transacciones);
        } catch (CreacionException e) {
            log.error("Error al obtener transacciones");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/depositos")
    public ResponseEntity<Void> depositar(@RequestBody TransaccionDTO transaccion) {
        log.info("Recibida solicitud para depositar monto en la cuenta. Detalles: {}", transaccion);
        try {
            this.transaccionService.depositar(transaccion);
            return ResponseEntity.noContent().build();
        } catch (CreacionException e) {
            log.error("Error al realizar el deposito");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/transferencias")
    public ResponseEntity<Void> transferir(@RequestBody TransaccionDTO transaccion, @RequestParam("monto") BigDecimal monto) {
        log.info("Recibida solicitud para realizar una transferencia. Detalles de la transacción: {}", transaccion);
        try {
            this.transaccionService.transferir(transaccion, monto);
            return ResponseEntity.noContent().build();
        } catch (CreacionException e) {
            log.error("Error al realizar la transferencia");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/retiros")
    public ResponseEntity<Void> retirar(@RequestBody TransaccionDTO transaccion) {
        log.info("Recibida solicitud para realizar un retiro. Detalles: {}", transaccion);
        try {
            this.transaccionService.retirar(transaccion);
            return ResponseEntity.noContent().build();
        } catch (CreacionException e) {
            log.error("Error al realizar el retiro");
            return ResponseEntity.badRequest().build();
        }
    }
}