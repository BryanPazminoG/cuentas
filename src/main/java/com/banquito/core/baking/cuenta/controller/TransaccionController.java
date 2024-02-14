package com.banquito.core.baking.cuenta.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.baking.cuenta.dto.TransaccionDTO;
import com.banquito.core.baking.cuenta.service.CreacionException;
import com.banquito.core.baking.cuenta.service.TransaccionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200", "http://34.173.161.134:4201", "http://34.176.205.203:4202", 
                        "http://34.176.102.118:4203", "http://34.176.137.180:4204"})
//@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
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
            return ResponseEntity.ok(this.transaccionService.obtenerPorId(id));
        } catch (RuntimeException rte) {
            log.error("Error al obtener la transacción: {}", rte);
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
            log.error("Error al generar la transaccion: {}", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/depositos")
    public ResponseEntity<Integer> depositarMonto(@RequestBody Map<String, Object> requestBody) {
        log.info("Recibida solicitud para depositar monto en la cuenta. Detalles: {}", requestBody);

        try {
            String numeroCuenta = (String) requestBody.get("numeroCuenta");
            BigDecimal valorDebe = new BigDecimal(requestBody.get("valorDebe").toString());
            Integer codigo =this.transaccionService.depositar(numeroCuenta, valorDebe);
            return ResponseEntity.ok(codigo);
        } catch (RuntimeException rte) {
            log.error("Error al realizar el deposito", rte);
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
            log.error("Error al obtener transacciones", e);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/transferencias")
    public ResponseEntity<Void> Transferencia(@RequestBody TransaccionDTO transaccion) {
        log.info("Recibida solicitud para realizar una transferencia. Detalles de la transacción: {}", transaccion);
        try {
            this.transaccionService.transferir(transaccion);
            return ResponseEntity.noContent().build();
        } catch (CreacionException e) {
            log.error("Error al realizar la transferencia", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/retiros")
    public ResponseEntity<Void> retirar(@RequestBody Map<String, Object> requestBody) {
        log.info("Recibida solicitud para realizar un retiro. Detalles: {}", requestBody);

        try {
            String numeroCuenta = (String) requestBody.get("numeroCuenta");
            BigDecimal valorHaber = new BigDecimal(requestBody.get("valorHaber").toString());
            this.transaccionService.retirar(numeroCuenta, valorHaber);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte) {
            log.error("Error al realizar el retiro", rte);
            return ResponseEntity.badRequest().build();
        }
    }

}